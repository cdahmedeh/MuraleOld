package net.cdahmedeh.murale.util.io;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by cdahmedeh on 1/28/2017.
 */
public class IOTools {
    public static void safeClose(Closeable... closeables) {
        for (Closeable closable : closeables) {
            try {
                closable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
