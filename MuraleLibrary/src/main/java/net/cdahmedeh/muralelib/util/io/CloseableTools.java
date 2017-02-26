package net.cdahmedeh.muralelib.util.io;

import net.cdahmedeh.muralelib.logging.Logging;

import java.io.Closeable;
import java.io.IOException;

/**
 * Methods for simplifying closing closeables.
 *
 * Created by cdahmedeh on 1/28/2017.
 */
public class CloseableTools {
    public static void safeClose(Closeable... closeables) {
        for (Closeable closable : closeables) {
            try {
                closable.close();
            } catch (IOException e) {
                Logging.warn(e, "Failed to close closeable %s", closable);
            }
        }
    }
}
