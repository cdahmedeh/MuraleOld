package net.cdahmedeh.muralelib.util.sys;

/**
 * Simplified sleep method.
 *
 * Created by cdahmedeh on 2/7/2017.
 */
public class SleepTools {
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }
}
