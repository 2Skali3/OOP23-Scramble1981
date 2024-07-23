package scramble1981_remastered.model.map;

import java.util.Arrays;
import java.util.List;

public class LandScapePart {

    public static enum LandScapeSprite {
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
    
        POINTY_CEILING,
        STALACTITE_CEILING,
        FLAT_CEILING_2,
        GORGE_CEILING,
        LEFT_ROUND_CEILING,
        RIGHT_ROUND_CEILING,
        LEFT_CROWN_CEILING,
        RIGHT_CROWN_CEILING,
        BLOB_SQUARE,
        GRAY_SQUARE,

        SKY
    }

    public static List<LandScapeSprite> getImageParts() {
        return Arrays.asList(LandScapeSprite.values());
    }

}
