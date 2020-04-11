package DatabaseConnector;


import PlatformGameShared.Enums.LoginState;
import PlatformGameShared.Enums.RegisterState;
import PlatformGameShared.PlatformLogger;
import PlatformGameShared.PropertiesLoader;
import com.mysql.jdbc.CommunicationsException;
import interfaces.ILoginDatabaseConnector;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;

public class LoginDatabaseJDBC implements ILoginDatabaseConnector {
    private static final String hostname = PropertiesLoader.getPropValues("jdbc.hostname", "jdbc.properties");
    private static final String port = PropertiesLoader.getPropValues("jdbc.port", "jdbc.properties");
    private static final String databaseName = PropertiesLoader.getPropValues("jdbc.databasename", "jdbc.properties");
    private static final String dbUserName = PropertiesLoader.getPropValues("jdbc.username", "jdbc.properties");
    private static final String dbPassword = PropertiesLoader.getPropValues("jdbc.password", "jdbc.properties");
    private static final String secret = PropertiesLoader.getPropValues("jdbc.secret", "jdbc.properties");
    private static String connectionString;

    private static SecretKeySpec secretKey;
    private static byte[] key;

    static LoginDatabaseJDBC instance = null;

    Connection con;

    private LoginDatabaseJDBC() {
        connectionString = "jdbc:mysql://" + hostname + ":" + port + "/" + databaseName;
        System.out.println("Connect string is " + connectionString);
    }

    public static LoginDatabaseJDBC getInstance() {
        if (instance == null) instance = new LoginDatabaseJDBC();
        return instance;
    }

    @Override
    public LoginState loginPlayer(String name, String password) {
        String encryptedPassword = encrypt(password, secret);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(connectionString, dbUserName, dbPassword);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select name, password from player where name = '" + name + "';");
            while (rs.next()) {
                String pwGet = rs.getString("password");
                if (encryptedPassword.equals(pwGet)) return LoginState.SUCCESS;

            }
            con.close();
            rs.close();
        } catch (SQLException e) {
            PlatformLogger.Log(Level.SEVERE, "Error trying to log in user :" + name + " :" + e.getMessage());
            e.printStackTrace();
            return LoginState.ERROR;
        }
        return LoginState.INCORRECTDATA;
    }

    @Override
    public RegisterState registerPlayer(String name, String password) {
        if (name.length() <= 3 || password.length() <= 6) return RegisterState.INCORRECTDATA;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(connectionString, dbUserName, dbPassword);
            Statement statement = con.createStatement();
            String encryptedPW = encrypt(password, secret);
            ResultSet rs = statement.executeQuery("select name from player where name = '" + name + "';");
            while (rs.next()) {
                String nameGet = rs.getString("name");
                if (name.equals(nameGet)) return RegisterState.ALREADYEXISTS;
                con.close();
            }
            rs.close();
        } catch (SQLException e) {
            PlatformLogger.Log(Level.SEVERE, "Error trying to register user :" + name + " :" + e.getMessage());
            e.printStackTrace();
            return RegisterState.ERROR;
        }
        try {
            con = DriverManager.getConnection(connectionString, dbUserName, dbPassword);
            Statement statement = con.createStatement();
            String encryptedPW = encrypt(password, secret);
            statement.execute("INSERT INTO `player` (`name`, `password`, `score`) VALUES ('" + name + "', '" + encryptedPW + "', '0')");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return RegisterState.ERROR;
        }
        return RegisterState.SUCCESS;

    }

    @Override
    public void AddGame(String map, String victor, String[] players) {
        boolean victorInNames = false;
        for (String player : players) {
            if (player.equals(victor)) {
                victorInNames = true;
                break;
            }
        }
        if (!victorInNames) {
            PlatformLogger.Log(Level.SEVERE, "Tried to add a match where the victor was not in the game!");
            return;
        }
        try {
            con = DriverManager.getConnection(connectionString, dbUserName, dbPassword);

            PreparedStatement gameStatement;
            PreparedStatement currGamePlayerStatement;

            String gameQuery = "INSERT INTO `game` (`map`, `victor`)" +
                    " VALUES (?, ?)";
            String gamePlayerQuery = "INSERT INTO `game_player` (`player_name`, `game_id` ) " +
                    "VALUES (?, ?)";
            gameStatement = con.prepareStatement(gameQuery, Statement.RETURN_GENERATED_KEYS);
            gameStatement.setString(1, map);
            gameStatement.setString(2, victor);
            gameStatement.addBatch();
            gameStatement.execute();
            ResultSet rs = gameStatement.getGeneratedKeys();
            int gameKey = 0;
            while (rs.next()) {
                gameKey = rs.getInt(1);
            }
            for (String player : players) {
                currGamePlayerStatement = con.prepareStatement(gamePlayerQuery);
                currGamePlayerStatement.setString(1, player);
                currGamePlayerStatement.setInt(2, gameKey);
                currGamePlayerStatement.execute();
            }
            con.close();
        } catch (SQLException e) {
            PlatformLogger.Log(Level.SEVERE, "Error trying set match data: :" + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void resetData() {
        try {
            DriverManager.registerDriver(new Driver());
            con = DriverManager.getConnection(connectionString, dbUserName, dbPassword);
            Statement statement = con.createStatement();
            statement.execute("SET FOREIGN_KEY_CHECKS = 0;");
            statement.execute("TRUNCATE TABLE game;");
            statement.execute("TRUNCATE TABLE game_player;");
            statement.execute("TRUNCATE TABLE player;");
            statement.execute("SET FOREIGN_KEY_CHECKS = 1;");
            con.close();
        } catch (SQLException e) {
            PlatformLogger.Log(Level.SEVERE, "Error trying reset data: :" + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            PlatformLogger.Log(Level.SEVERE, "Error trying set encryption key: :" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String encrypt(String strToEncrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            PlatformLogger.Log(Level.SEVERE, "Error while encrypting: " + e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String strToDecrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            PlatformLogger.Log(Level.SEVERE, "Error while decrypting: " + e.toString());
            e.printStackTrace();
        }
        return null;
    }


}
