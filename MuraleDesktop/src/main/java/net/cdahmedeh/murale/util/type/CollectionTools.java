package net.cdahmedeh.murale.util.type;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by cdahmedeh on 1/30/2017.
 */
public class CollectionTools {
    /**
     * Returns a random element from the provided list.
     *
     * @param list The list to pick from.
     * @return A random element from the list.
     */
    public static <E> E pickRandom(Collection<E> e) {
        return e.stream()
                .skip((int) (e.size() * Math.random()))
                .findFirst().get();
    }
}
