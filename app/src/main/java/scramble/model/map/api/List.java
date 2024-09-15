package scramble.model.map.api;

/**
 * Interface for MapStage, this interface will be implemented with a private
 * data strucure that contains
 * the columns that form the stage and will store them in a sequential way.
 * 
 * @param <X> the type of element designed to rappresent the data contained into
 *            the
 *            {@code MapStage}
 */
public interface List<X> {
    /**
     * Method to insert a new column in MapStage.
     * This method store columns sequentially.
     * 
     * @param column value of the new column
     */
    void addColumn(X column);

    /**
     * Getter for a column of the MapStage that is identified by an index.
     * 
     * @param index position of the column that we want to get
     * @return a column of the MapStage, identified by the index, as a PairImpl of
     *         MapElement;
     *         the first element of Pair rappresents the ceiling, the second
     *         rappresents the floor
     */
    X getColumn(int index);

    /**
     * The size of the MapStage internal elements.
     * 
     * @return number of columns in MapStage
     */
    int size();
}
