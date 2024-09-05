package scramble.model.common.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Collection;
import java.util.stream.Stream;

import scramble.model.common.api.TimedLinkedList;


/**
 * A TimedLinkedList is a custom list implementation where each element is automatically
 * removed after a specified time interval. It internally uses a {@link LinkedList} to store
 * the elements and a {@link ScheduledExecutorService} to schedule their removal.
 *
 * @param <T> the type of elements held in this list
 */
public class TimedLinkedListImpl<T> implements TimedLinkedList<T> {
    private final List<T> list = new LinkedList<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addElement(final T element, final long time) {
        final var ret = list.add(element);

        // Schedule removal after 5 seconds
        scheduler.schedule(() -> {
            list.remove(element);
        }, time, TimeUnit.MILLISECONDS);
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(final Collection<T> c, final long time) {
        return c.stream().map(x -> addElement(x, time)).anyMatch(x -> x);
        /*
            B0, B1, B2, B3, ..., B8, B9, ...
            true, true, true, true, ..., false, false, ...
        */
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<T> stream() {
        return list.stream();
    }
}
