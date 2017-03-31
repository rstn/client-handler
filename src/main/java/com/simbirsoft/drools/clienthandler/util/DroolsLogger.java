package com.simbirsoft.drools.clienthandler.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DroolsLogger {

    private static Logger log = LoggerFactory.getLogger("ClientRules");

    public static void info(final String message){
        log.info(message);
    }

    public static void debug(final String message){
        log.debug(message);
    }

    public static void warn(final String message){
        log.warn(message);
    }

    public static void error(final String message){
        log.error(message);
    }
}
