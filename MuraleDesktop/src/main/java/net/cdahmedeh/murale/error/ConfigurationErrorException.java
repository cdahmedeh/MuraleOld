package net.cdahmedeh.murale.error;

/**
 * Created by cdahmedeh on 1/28/2017.
 *
 * Happens during internet connectivity problem.
 */
public class ConfigurationErrorException extends RuntimeException {
    public ConfigurationErrorException(String message, Exception cause) {
        super(message, cause);
    }
}
