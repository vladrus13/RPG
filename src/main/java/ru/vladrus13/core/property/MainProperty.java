package ru.vladrus13.core.property;

import ru.vladrus13.core.utils.Writer;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Main properties of app
 */
public class MainProperty {

    /**
     * Logger
     */
    private final static Logger logger = Logger.getLogger(MainProperty.class.getName());
    /**
     * Main properties
     */
    private static Properties properties;

    /**
     * Read properties from properties/main.properties
     */
    public static void read() {
        properties = new Properties();
        try {
            properties.load(MainProperty.class.getResourceAsStream("/properties/main.properties"));
        } catch (IOException e) {
            Writer.printStackTrace(logger, e);
            AppLock.exception();
        }
    }

    /**
     * Get property by name
     * @param s name of property
     * @return property
     */
    public static String get(String s) {
        return properties.getProperty(s);
    }

    /**
     * Get property by name in Long
     * @param s name of property
     * @return property in Long
     */
    public static long getLong(String s) {
        return Long.parseLong(properties.getProperty(s));
    }

    /**
     * Get property by name in Integer
     * @param s name of property
     * @return property in Integer
     */
    public static int getInteger(String s) {
        return Integer.parseInt(properties.getProperty(s));
    }

    /**
     * @return locale of app
     */
    public static Locale getLocale() {
        return Locale.forLanguageTag(properties.get("window.bundle").toString());
    }
}
