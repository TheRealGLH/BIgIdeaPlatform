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
import java.util.Arrays;
import java.util.Base64;

public class LoginDatabaseJDBC implements ILoginDatabaseConnector {
    private static final String hostname = PropertiesLoader.getPropValues("jdbc.domain", "jdbc.hostname");
    private static final String port = PropertiesLoader.getPropValues("jdbc.port", "jdbc.hostname");
    private static final String databasename = PropertiesLoader.getPropValues("jdbc.databasename", "jdbc.hostname");
    private static final String username = PropertiesLoader.getPropValues("jdbc.username", "jdbc.hostname");
    private static final String password = PropertiesLoader.getPropValues("jdbc.password", "jdbc.hostname");
    private static final String secret = PropertiesLoader.getPropValues("jdbc.secret", "jdbc.hostname");
    private static String connectionString;

    private static SecretKeySpec secretKey;
    private static byte[] key;

    static LoginDatabaseJDBC instance = null;

    private LoginDatabaseJDBC() {
        connectionString = hostname + port + "/" + databasename;
        System.out.println("Connect string is " + connectionString);
    }

    public static LoginDatabaseJDBC getInstance() {
        if (instance == null) instance = new LoginDatabaseJDBC();
        return instance;
    }

    @Override
    public LoginState loginPlayer(String name, String password) {
        throw new UnsupportedOperationException("The method <> has not yet been implemented");
    }

    @Override
    public RegisterState registerPlayer(String name, String password) {
        throw new UnsupportedOperationException("The method <> has not yet been implemented");
    }

    @Override
    public void resetData() {
        throw new UnsupportedOperationException("The method <> has not yet been implemented");
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
