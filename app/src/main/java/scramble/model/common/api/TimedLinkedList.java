package scramble.model.common.api;

import java.util.Collection;
import java.util.stream.Stream;
import java.util.List;

/**
 * TimedLinkedList interface defines the contract for a timed list where
 * elements are automatically
 * removed after a specified period of time.
 *
 * @param <T> the type of elements stored in this timed list
 */
public interface TimedLinkedList<T> {
    /**
     * Adds an element to the list and schedules its removal after the specified
     * time.
     *
     * @param element the element to be added
     * @param time    the time in milliseconds after which the element will be
     *                removed
     * @return {@code true} if the element was successfully added to the list
     */
    boolean addElement(T element, long time);

    /**
     * Adds all elements from the specified collection to the list, and schedules
     * each of them
     * to be removed after the specified time.
     *
     * @param c    the collection containing elements to be added
     * @param time the time in milliseconds after which each element will be removed
     * @return {@code true} if at least one element was added, {@code false}
     *         otherwise
     */
    boolean addAll(Collection<T> c, long time);

    /**
     * Returns a sequential {@code Stream} with the elements of this list.
     *
     * @return a stream of the elements in this list
     */
    Stream<T> stream();

    /**
     * Getter for list.
     * 
     * @return the list
     */
    List<T> getList();

}
