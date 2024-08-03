package scramble.model.map.utils;

import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.impl.MapElementImpl;
import scramble.model.map.utils.enums.LandscapePart;

/**
 * Class that converts MapStageImpl columns to ArrayList of BufferedImage.
 */
public final class Converter {

    private Converter() { }

    /**
     * Method to convert a MapStageImpl column into its ArrayList of BufferedImage.
     * 
     * @param column cupple of MapElementImpl to translate
     * @return an ArrayList of BufferedImage that represents the column
     */
    public static ArrayList<BufferedImage> convertMapStageImplToBufferedImage(
            final PairImpl<MapElementImpl, MapElementImpl> column) {
        ArrayList<BufferedImage> returnColumn = new ArrayList<>();
        LandscapeUtils mapUtils = new LandscapeUtils();
        BufferedImage green = mapUtils.getSprite(LandscapePart.GREEN_SQUARE);
        BufferedImage sky = mapUtils.getSprite(LandscapePart.SKY);
        int ceilingHeight = column.getFirstElement().getHeight();
        int floorHeight = column.getSecondElement().getHeight();
        boolean isGreen = (ceilingHeight != -1);
        for (int y = 0; y < LandscapeUtils.NUMBER_OF_SPITE_PER_STAGE_HEIGHT; y++) {
            if (y == ceilingHeight) {
                isGreen = false;
                returnColumn.add(column.getFirstElement().getSprite());
            } else if (y == floorHeight) {
                isGreen = true;
                returnColumn.add(column.getSecondElement().getSprite());
            } else if (isGreen) {
                returnColumn.add(green);
            } else {
                returnColumn.add(sky);
            }
        }
        return returnColumn;
    }
    /**
     * Method that insert all of the enum LandscapeSprite data into a List.
     * 
     * @return the list of all the enum LandscapeSprite
     */
    public static List<LandscapePart> getImageParts() {
        return Arrays.asList(LandscapePart.values());
    }
}
