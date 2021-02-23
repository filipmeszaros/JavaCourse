package org.company;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Log4J library enables you to write debug/info/error/fatal log messages either to console or to log file.
 * Framework contains multiple features.
 * For framework to work, you need to set up log4j2.xml configuration file to src/main/resources folder
 */
public class Log4JExample {
    private static Logger log = LogManager.getLogger(Log4JExample.class.getName());

    public static void main(String[] args) {
        log.debug("Debug message");
        log.info("Info message");
        log.error("Error message");
        log.fatal("Fatal message");
    }
}
