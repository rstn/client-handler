package com.simbirsoft.drools.clienthandler.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DroolsClientLogger {

    private static Logger LOGGER = LoggerFactory.getLogger(DroolsClientLogger.class);

    public static void info(final String message) {
        LOGGER.info(message);
    }

    public static void debug(final String message) {
        LOGGER.debug(message);
    }

    public static void warn(final String message) {
        LOGGER.warn(message);
    }

    public static void error(final String message) {
        LOGGER.error(message);
    }
}
