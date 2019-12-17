package DatabaseConnector;


import PlatformGameShared.Enums.LoginState;
import PlatformGameShared.Enums.RegisterState;
import PlatformGameShared.PropertiesLoader;
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
    public void resetData() {
        try {
            DriverManager.registerDriver(new Driver());
            con = DriverManager.getConnection(connectionString, dbUserName, dbPassword);
            Statement statement = con.createStatement();
            statement.execute("TRUNCATE TABLE player;");
            con.close();
        } catch (SQLException e) {
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
            System.out.println("Error while encrypting: " + e.toString());
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
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }


}
