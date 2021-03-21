package ru.vladrus13.core.property;

import java.util.Objects;

/**
 * Lock for app
 */
public class AppLock {
    /**
     * Current status of app
     */
    private static String status = "NONE";

    /**
     * Make status of app at exception
     */
    public static void exception() {
        status = "exception";
    }

    /**
     * Make status of app at close
     */
    public static void close() {
        status = "close";
    }

    /**
     * Is app in exception status
     *
     * @return is app in exception status
     */
    public static boolean isException() {
        return Objects.equals(status, "exception");
    }

    /**
     * Is app in close status
     *
     * @return is app in close status
     */
    public static boolean isClose() {
        return Objects.equals(status, "close");
    }

}
