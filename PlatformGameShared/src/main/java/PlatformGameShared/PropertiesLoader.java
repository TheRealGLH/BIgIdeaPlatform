package PlatformGameShared;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesLoader {

    static InputStream inputStream;
    private static Map<String, String> propertiesMap = new HashMap<>();

    public static String getPropValues(String propertyName, String propertiesFileName) {
        String property = propertiesMap.get(propertyName);
        if (property != null) return property;
        String result = null;
        try {
            Properties prop = new Properties();
            inputStream = PropertiesLoader.class.getClassLoader().getResourceAsStream(propertiesFileName);
            //TODO make a new file when there is no file found.
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propertiesFileName + "' not found in the classpath");
            }

            Date time = new Date(System.currentTimeMillis());

            // get the property value and print it out
            result = prop.getProperty(propertyName);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            try {
                assert inputStream != null;
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        propertiesMap.put(propertyName, result);
        return result;
    }
}
