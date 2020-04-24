package PlatformGameShared;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class PlatformLogger {
    private static Logger LOGGER = Logger.getLogger("PlatformLogger");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MMM-dd k-m-s-S");
    private static PlatformLogger instance = new PlatformLogger();
    private Handler fileHandler;
    private Formatter formatter;


    private PlatformLogger() {
        String name = System.getProperty("sun.java.command").replace('/', '.');
        Date today = new Date();
        String logPath = PropertiesLoader.getPropValues("logger.logPath", "logger.properties") + name;

        //Check if the path exists. if not, we make it
        File dirCheck = new File(logPath);
        dirCheck.mkdirs();
        try {
            fileHandler = new FileHandler(logPath + "\\" + dateFormat.format(today) + ".log");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error initializing the logger.", e);
        }
        formatter = new SimpleFormatter();
        LOGGER.addHandler(fileHandler);
        LOGGER.setLevel(Level.ALL);
        fileHandler.setFormatter(formatter);
        //TODO have this be set based on the config
        fileHandler.setLevel(Level.ALL);
    }


    public static void Log(Level level, String message) {
        LOGGER.log(level, message);
    }

    public static void Log(Level level, String message, Object object) {
        LOGGER.log(level, message, object);
    }


}
