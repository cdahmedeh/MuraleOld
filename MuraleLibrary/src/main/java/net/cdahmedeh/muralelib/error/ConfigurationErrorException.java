package net.cdahmedeh.muralelib.error;

/**
 * Happens when configuration parsing error occurs.
 *
 * Created by cdahmedeh on 1/28/2017.
 */
public class ConfigurationErrorException extends RuntimeException {
    public ConfigurationErrorException(String message, Exception cause) {
        super(message, cause);
    }
}
