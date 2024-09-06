package scramble.controller.map;

import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import scramble.model.common.api.Pair;
import scramble.model.common.impl.PairImpl;
import scramble.model.map.impl.MapElement;
import scramble.model.map.util.LandUtils;
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
}
