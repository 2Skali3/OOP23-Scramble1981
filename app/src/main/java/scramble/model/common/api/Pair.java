package scramble.model.common.api;

/**
 * Interface for a generic Pair class that can contain two different types of
 * element.
 * 
 * @param <X> type of the first element stored in Pair
 * @param <Y> type of the second element stored in Pair
 */
public interface Pair<X, Y> {
    /**
     * Getter for the first element of the Pair interface.
     * 
     * @return the first element of the Pair
     */
    X getFirstElement();

    /**
     * Getter for the second element of the Pair interface.
     * 
     * @return the second element of the Pair
     */
    Y getSecondElement();

    /**
     * Setter for the first element of the Pair interface.
     * 
     * @param firstElement the new value of the first element of the Pair
     */
    void setFirstElement(X firstElement);

    /**
     * Setter for the second element of the Pair interface.
     * 
     * @param secondElement the new value of the second element of the Pair
     */
    void setSecondElement(Y secondElement);
}
