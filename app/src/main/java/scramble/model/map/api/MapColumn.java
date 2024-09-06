package scramble.model.map.api;

import java.util.List;

import scramble.model.map.impl.MapElement;

import java.awt.image.BufferedImage;

/**
 * {@code MapColum} is an interface for the rappresentation of a single column
 * of the landscape.
 */
public interface MapColumn {

    /**
     * Getter for the ceiling part of the column.
     * 
     * @return the ceiling part
     */
    List<MapElement> getElements();

    /**
     * Getter for the ceiling part that is composed by BufferedImage.
     * 
     * @return the list of only BufferedImage part
     */
    List<BufferedImage> getBIs();

    /**
     * Getter for the x coordinate.
     * @return the x coordinate of the column
     */
    int getX();

    /**
     * Update the x coordinate of the column.
     * @param x the new coordinate for the column in the x-axis
     */
    void updateX(int x);

    /**
     * Method for updating the x-axis of the column of the HitBox.
     * @param x update to sum at the old hitbox coordinate
     */
    void updateHitBox(int x);

    /**
     * Getter for the width of the BufferedImage element of the column.
     * @return the width of the BufferedImage of the column
     */
    int getBIListWidth();

    /**
     * Getter for the height of the BufferedImage element of the column.
     * @return the height of the BufferedImage of the column
     */
    int getBIListHeight();
}
