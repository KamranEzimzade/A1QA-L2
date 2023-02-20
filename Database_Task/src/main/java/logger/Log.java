package logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public final class Log {


    private Log() {
    }

    private static final Logger log = LogManager.getLogger("Logger");

    public static void info(String text) {
        log.info(text);
    }

    public static void warn(String text) {
        log.warn(text);
    }

    public static void error(String text) {
        log.error(text);
    }
}
