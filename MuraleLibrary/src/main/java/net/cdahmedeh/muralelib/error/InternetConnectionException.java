package net.cdahmedeh.muralelib.error;

/**
 * Created by cdahmedeh on 1/28/2017.
 *
 * Happens during internet connectivity problem.
 */
public class InternetConnectionException extends RuntimeException {
    public InternetConnectionException(String message, Exception cause) {
        super(message, cause);
    }
}
