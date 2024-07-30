package scramble.model.common.api;

/**
 * Interface for screen location management.
 */
public interface Point2D extends Cloneable {

    /**
     * Getter for the X axis component.
     * 
     * @return X coordinate
     */
    int getX();

    /**
     * Getter for the Y axis component.
     * 
     * @return Y coordinate
     */
    int getY();

    /**
     * Setter for the location.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     */
    void setLocation(int x, int y);

}
