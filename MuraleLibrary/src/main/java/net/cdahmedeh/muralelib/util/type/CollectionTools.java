package net.cdahmedeh.muralelib.util.type;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Methods for dealing with collections and lists.
 *
 * Created by cdahmedeh on 1/30/2017.
 */
public class CollectionTools {
    /**
     * Returns a random element from the provided collection.
     *
     * @param c The list to pick from.
     * @return A random element from the list.
     */
    public static <E> E pickRandom(Collection<E> c) {
        int randomIndex = ThreadLocalRandom.current().nextInt(c.size());
        return c.stream()
                .skip(randomIndex)
                .findFirst()
                .get();
    }
}
