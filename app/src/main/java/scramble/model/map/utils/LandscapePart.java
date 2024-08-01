package scramble.model.map.utils;

import java.util.Arrays;
import java.util.List;

public class LandscapePart {

    public static enum LandscapeSprite {
        ROUND_CLIMB,
        BOOT_CLIMB,
        CROWN_CLIMB,
        STANDARD_CLIMB,
    
        MIRROR_ROUND_CLIMB,
        MIRROR_BOOT_CLIMB,
        MIRROR_CROWN_CLIMB,
        MIRROR_STANDARD_CLIMB,
    
        TOP_FLAT_FLOOR,
        TRIANGLE_CLIFF,
        BOTTOM_FLAT_FLOOR,
    
        GORGE_FLOOR,
        FLAT_CEILING_1,
        LITTLE_HILL,
        GREEN_SQUARE,
        WHITE_SQUARE,
        LIGHT_BRICK_WALL, // last image of the first row
        DARK_BRICK_WALL,
    
        LONG_GREEN_RECTANGLE_START,
        LONG_GREEN_RECTANGLE_MIDDLE,
        LONG_GREEN_RECTANGLE_END,
        WHITE_GREEN_RECTANGLE_START,
        WHITE_GREEN_RECTANGLE_MIDDLE,
        WHITE_GREEN_RECTANGLE_END,
    
        FLAT_CEILING_DOWN, // FLAT
        STALACTITE_CEILING,
        FLAT_CEILING_UP,
        GORGE_CEILING,  // FLAT
        LEFT_ROUND_CEILING, // DOWN
        RIGHT_ROUND_CEILING, // UP
        LEFT_CROWN_CEILING, // DOWN
        RIGHT_CROWN_CEILING, // UP
        BLOB_SQUARE,
        GRAY_SQUARE,

        SKY
    }

    public static List<LandscapeSprite> getImageParts() {
        return Arrays.asList(LandscapeSprite.values());
    }

}
