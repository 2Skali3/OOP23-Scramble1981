package scramble.model.map.utils;

import java.util.Arrays;
import java.util.List;

/**
 * Class that contains enum for the management of the Landscape Sprite and a
 * relative method for translating the enum to a List.
 */
public final class LandscapePart {

    /**
     * Enum for the Landscape sprite denomination for the Landscape parts.
     */
    public enum LandscapeSprite {
        /**
         * Descending floor landscape.
         */
        ROUND_CLIMB,
        /**
         * Descending floor landscape.
         */
        BOOT_CLIMB,
        /**
         * Descending floor landscape.
         */
        CROWN_CLIMB,
        /**
         * Descending floor landscape.
         */
        STANDARD_CLIMB,

        /**
         * Ascending floor landscape.
         */
        MIRROR_ROUND_CLIMB,
        /**
         * Ascending floor landscape.
         */
        MIRROR_BOOT_CLIMB,
        /**
         * Ascending floor landscape.
         */
        MIRROR_CROWN_CLIMB,
        /**
         * Ascending floor landscape.
         */
        MIRROR_STANDARD_CLIMB,
        /**
         * Flat floor landscape.
         */
        TOP_FLAT_FLOOR,
        /**
         * Flat floor landscape.
         */
        TRIANGLE_CLIFF,
        /**
         * Flat floor landscape.
         */
        BOTTOM_FLAT_FLOOR,

        /**
         * Summit floor landscape.
         */
        GORGE_FLOOR,
        /**
         * Flat ceiling landscape.
         */
        FLAT_CEILING_1,
        /**
         * Flat floor landscape.
         */
        LITTLE_HILL,
        /**
         * Green square.
         */
        GREEN_SQUARE,
        /**
         * White square.
         */
        WHITE_SQUARE,
        /**
         * Light brick wall.
         */
        LIGHT_BRICK_WALL, // last image of the first row
        /**
         * Dark brick wall.
         */
        DARK_BRICK_WALL,
        /**
         * Long green rectangel start.
         */
        LONG_GREEN_RECTANGLE_START,
        /**
         * Long green rectangel middle.
         */
        LONG_GREEN_RECTANGLE_MIDDLE,
        /**
         * Long green rectangel end.
         */
        LONG_GREEN_RECTANGLE_END,
        /**
         * Long white rectangel start.
         */
        LONG_WHITE_RECTANGLE_START,
        /**
         * Long white rectangel middle.
         */
        LONG_WHITE_RECTANGLE_MIDDLE,
        /**
         * Long white rectangel end.
         */
        LONG_WHITE_RECTANGLE_END,
        /**
         * Flat ceiling landscape.
         */
        FLAT_CEILING_DOWN, // FLAT
        /**
         * Summit ceiling landscape.
         */
        STALACTITE_CEILING,
        /**
         * Flat ceiling landscape.
         */
        FLAT_CEILING_UP,
        /**
         * Flat ceiling landscape.
         */
        GORGE_CEILING, // FLAT
        /**
         * Descending ceiling landscape.
         */
        LEFT_ROUND_CEILING, // DOWN
        /**
         * Ascending ceiling landscape.
         */
        RIGHT_ROUND_CEILING, // UP
        /**
         * Descending ceiling landscape.
         */
        LEFT_CROWN_CEILING, // DOWN
        /**
         * Ascending ceiling landscape.
         */
        RIGHT_CROWN_CEILING, // UP
        /**
         * Blob square.
         */
        BLOB_SQUARE,
        /**
         * Gray square.
         */
        GRAY_SQUARE,
        /**
         * Sky.
         */
        SKY
    }

    private LandscapePart() { }

    /**
     * Method that insert all of the enum LandscapeSprite data into a List.
     * 
     * @return the list of all the enum LandscapeSprite
     */
    public static List<LandscapeSprite> getImageParts() {
        return Arrays.asList(LandscapeSprite.values());
    }
}
