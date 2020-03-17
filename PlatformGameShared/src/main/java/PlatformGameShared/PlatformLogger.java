package PlatformGameShared;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class PlatformLogger {
    private static Logger LOGGER = Logger.getLogger("PlatformLogger");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MMM-dd k-m-s-S");
    private static PlatformLogger instance = new PlatformLogger();
    private Handler fileHandler;
    private Formatter formatter;


    private PlatformLogger() {
        Date today = new Date();
        try {
            //TODO have the path be set in the config
            fileHandler = new FileHandler("./" + System.getProperty("sun.java.command") + " " + dateFormat.format(today) + ".log");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error initializing the logger.", e);
        }
        formatter = new SimpleFormatter();
        LOGGER.addHandler(fileHandler);
        fileHandler.setFormatter(formatter);
        //TODO have this be set based on the config
        fileHandler.setLevel(Level.ALL);
    }

    /*
    public static PlatformLogger instance() {
        if (instance == null) {
            instance = new PlatformLogger();
        }
        return instance;
    }
     */

    public static void Log(Level level, String message) {
        LOGGER.log(level, message);
    }

    public static void Log(Level level, String message, Object object) {
        LOGGER.log(level, message, object);
    }


}
