package ru.vladrus13.core.property;

import ru.vladrus13.core.utils.Writer;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class MainProperty {

    private final static Logger logger = Logger.getLogger(MainProperty.class.getName());
    private static Properties properties;

    public static void read() {
        properties = new Properties();
        try {
            properties.load(MainProperty.class.getResourceAsStream("/properties/main.properties"));
        } catch (IOException e) {
            Writer.printStackTrace(logger, e);
            GameLock.exception();
        }
    }

    public static String get(String s) {
        return properties.getProperty(s);
    }

    public static long getLong(String s) {
        return Long.parseLong(properties.getProperty(s));
    }

    public static int getInteger(String s) {
        return Integer.parseInt(properties.getProperty(s));
    }
}
