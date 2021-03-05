package ru.vladrus13.core.property;

import java.util.Objects;

public class GameLock {
    private static String status = "NONE";

    public static void exception() {
        status = "exception";
    }

    public static boolean isException() {
        return Objects.equals(status, "exception");
    }
}
