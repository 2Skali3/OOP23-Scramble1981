package scramble.model.map.api;

import java.util.List;

import scramble.model.common.api.Pair;
import scramble.model.map.impl.MapElement;

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
    void addColumn(Pair<MapElement, MapElement> column);

    /**
     * Setter for the ceiling part of the stage.
     * 
     * @param ceiling the list of {@link MapElement} that form the ceiling
     */
    void setCeiling(List<MapElement> ceiling);

    /**
     * Setter for the floor part of the stage.
     * 
     * @param floor the list of {@link MapElement} that form the floor
     */
    void setFloor(List<MapElement> floor);

    /**
     * Getter for a column of the MapStage that is identified by an index.
     * 
     * @param index position of the column that we want to get
     * @return a column of the MapStage, identified by the index, as a PairImpl of
     *         MapElement;
     *         the first element of Pair rappresents the ceiling, the second
     *         rappresents the floor
     */
    Pair<MapElement, MapElement> getColumn(int index);

    /**
     * Getter for only the ceiling component of the column.
     * 
     * @param index of the column
     * @return the ceiling part of the column
     */
    MapElement getCloumnCeiling(int index);

    /**
     * Getter for only the floor component of the column.
     * 
     * @param index of the column
     * @return the floor part of the column
     */
    MapElement getCloumnFloor(int index);

    /**
     * The size of the MapStage internal elements.
     * 
     * @return number of columns in MapStage
     */
    int size();

    /**
     * Getter for only the ceiling part of the MapStage.
     * 
     * @return the ceiling part of the stage
     */
    List<MapElement> getCeiling();

    /**
     * Getter for only the floor part of the MapStage.
     * 
     * @return the floor part of the stage
     */
    List<MapElement> getFloor();
}
