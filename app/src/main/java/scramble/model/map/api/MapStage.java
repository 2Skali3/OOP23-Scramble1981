package scramble.model.map.api;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.impl.MapElementImpl;

/**
 * Interface for MapStage, this interface will be implemented with a private
 * data strucure that contains
 * the columns that form the stage and will store them in a sequential way.
 */
public interface MapStage {
    /**
     * Method to insert a new column in MapStage.
     * This method store columns sequentially.
     * 
     * @param column value of the new column
     */
    void addColumn(PairImpl<MapElementImpl, MapElementImpl> column);

    /**
     * Getter for a column of the MapStage that is identified by an index.
     * 
     * @param index position of the column that we want to get
     * @return a column of the MapStage, identified by the index, as a PairImpl of
     *         MapElement;
     *         the first element of PairImpl rappresents the ceiling, the second
     *         rappresents the floor
     */
    PairImpl<MapElementImpl, MapElementImpl> getColumn(int index);

    /**
     * The size of the MapStage data structure.
     * 
     * @return number of columns in MapStage
     */
    int size();
}