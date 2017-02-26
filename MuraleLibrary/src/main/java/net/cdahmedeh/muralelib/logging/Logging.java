package net.cdahmedeh.muralelib.logging;

import org.slf4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.slf4j.LoggerFactory.*;

/**
 * Created by cdahmedeh on 2/11/2017.
 */
public class Logging {
    private static final Logger logger = getLogger(Logging.class);

    public static void warn(Exception exception, String message, Object... args) {
        logger.warn(String.format(message, args), exception);
    }

    public static void error(Exception exception, String message, Object... args) {
        logger.error(String.format(message, args), exception);
    }

    public static void warn(String message) {
        logger.warn(message);
    }
}
