package net.cdahmedeh.muralelib.error;

/**
 * When a file or resource cannot be found.
 *
 * Created by cdahmedeh on 1/28/2017.
 */
public class ResourceMissingException extends RuntimeException {
    public ResourceMissingException(String message) {
        super(message);
    }
}
