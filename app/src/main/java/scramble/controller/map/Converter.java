package scramble.controller.map;

import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import scramble.model.common.api.Pair;
import scramble.model.common.impl.PairImpl;
import scramble.model.map.api.MapStage;
import scramble.model.map.impl.MapElement;
import scramble.model.map.util.LandUtils;
import scramble.model.map.util.enums.LandBehaviour;
import scramble.model.map.util.enums.LandPart;

/**
 * Class that convert raw data to usable data.
 */
public final class Converter {

    private Converter() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Method that take convert MapElements in List<BufferedImage>.
     * 
     * @param column rappresented by Pair of MapElement
     * @return displayable pair of BfferedImage list
     */
    public static Pair<List<BufferedImage>, List<BufferedImage>> convertMapElementToListOfBufferedImaga(
            final Pair<MapElement, MapElement> column) {

        final ArrayList<BufferedImage> ceiling = new ArrayList<>();
        final ArrayList<BufferedImage> floor = new ArrayList<>();
        final BufferedImage green = LandUtils.getSprite(LandPart.GREEN_SQUARE);

        final int ceilingHeight = column.getFirstElement().getPosition().getSecondElement()
                * LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE;
        final int floorHeight = column.getSecondElement().getPosition().getSecondElement()
                * LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE;

        for (int y = 0; y * LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE < ceilingHeight; y++) {
            ceiling.add(green);
        }
        ceiling.add(column.getFirstElement().getSprite());

        for (int y = LandUtils.NUMBER_OF_SPITE_PER_STAGE_HEIGHT; y
                * LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE > floorHeight; y--) {
            floor.add(green);
        }
        floor.add(column.getSecondElement().getSprite());

        return new PairImpl<List<BufferedImage>, List<BufferedImage>>(ceiling, floor);
    }

    // to-do: Porta in Converter la logica del Convertire enum in BufferedImage
    /**
     * Method that insert all of the enum LandscapeSprite data into a List.
     * 
     * @return the list of all the enum LandscapeSprite
     */
    public static List<LandPart> getImageParts() {
        return Arrays.asList(LandPart.values());
    }

    /**
     * Method that from the MapStage data of a single column (the ceiling and the
     * floor MapElement) and
     * return the list of all the MapElements that are present on that column.
     * 
     * @param ceiling component of the column
     * @param floor   component of the column
     * @param x       position of the column
     * @return a list of MapElement with all the element and background element
     *         present in that column
     */
    private static List<MapElement> elaborateSingleColumn(
            final MapElement ceiling, final MapElement floor, final int x) {
        final List<MapElement> returnColumn = new ArrayList<>();
        boolean isLandscapeArea = true;
        final BufferedImage green = LandUtils.getSprite(LandPart.GREEN_SQUARE);
        if (ceiling.getPosition().getSecondElement() == -1) {
            isLandscapeArea = false;
        }

        returnColumn.add(floor);
        returnColumn.add(ceiling);

        for (int y = 0; y < LandUtils.NUMBER_OF_SPITE_PER_STAGE_HEIGHT; y++) {
            if (y * LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE == ceiling.getPosition().getSecondElement()) {
                isLandscapeArea = false;
            } else if (y == floor.getPosition().getSecondElement()) {
                isLandscapeArea = true;
            } else if (isLandscapeArea) {
                returnColumn.add(new MapElement(x, y,
                        LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE,
                        LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE,
                        green, ceiling.getTerrainType(), LandBehaviour.EMPTY));
            }
        }

        return returnColumn;

    }

    // to-do: nuova classe Column
    /**
     * Method that takes all the MapStages you want to process
     * and translates them into a list of columns (List of MapElement).
     * 
     * @param mapStages List of MapStage to elaborate
     * @return column of the merged mapStage rappresented as a list of column (List
     *         of MapElement)
     */
    public static List<List<MapElement>> convertMapStage(final List<MapStage> mapStages) {
        final List<List<MapElement>> returnColumns = new ArrayList<>();
        int x = 0;
        for (final MapStage stage : mapStages) {
            for (int i = 0; i < mapStages.size(); i++) {
                returnColumns.add(elaborateSingleColumn(stage.getCloumnCeiling(i), stage.getCloumnFloor(i), x));
                x++;
            }
        }
        return returnColumns;
    }

}
