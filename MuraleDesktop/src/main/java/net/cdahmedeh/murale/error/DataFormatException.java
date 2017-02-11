package net.cdahmedeh.murale.error;

/**
 * Created by cdahmedeh on 1/28/2017.
 */
public class DataFormatException extends RuntimeException {
    public DataFormatException(String message, Exception cause) {
        super(message, cause);
    }
}
