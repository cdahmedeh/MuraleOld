package net.cdahmedeh.murale.error;

/**
 * Created by cdahmedeh on 1/28/2017.
 *
 * Happens during internet connectivity problem.
 */
public class ResourceMissingException extends RuntimeException {
    public ResourceMissingException(String message) {
        super(message);
    }
}
