package net.cdahmedeh.murale.util.sys;

/**
 * Created by cdahmedeh on 2/7/2017.
 */
public class TimeTools {
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }
}
