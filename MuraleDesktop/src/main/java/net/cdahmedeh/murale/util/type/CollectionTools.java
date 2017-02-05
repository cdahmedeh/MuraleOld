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
    public static <E> E pickRandom(Collection<E> list) {
        int size = list.size();
        int randomIndex = ThreadLocalRandom.current().nextInt(size);

        Iterator<E> iterator = list.iterator();
        E value = null;
        for (int i = 0; i < randomIndex; i++) {
            value = iterator.next();
        }

        return value;
    }
}
