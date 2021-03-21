package ru.vladrus13.core.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * Writer class
 */
public class Writer {
    /**
     * Print to logger stacktrace
     *
     * @param logger logger
     * @param e      exception
     */
    public static void printStackTrace(Logger logger, Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        logger.severe(stringWriter.toString());
    }
}
