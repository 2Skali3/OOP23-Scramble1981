package scramble.model.map.util;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import scramble.controller.map.Converter;
import scramble.model.map.util.enums.LandPart;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.logging.Logger;

import java.awt.image.BufferedImage;

/**
 * Class that contains all the methods and constants to manage the landscape
 * management.
 */
public final class LandUtils {
    /**
     * Number of sprites per stage per column.
     */
    public static final int NUMBER_OF_SPITE_PER_STAGE_HEIGHT = 40;
    /**
     * Number of sprites per stage per row.
     */
    public static final int NUMBER_OF_SPITE_PER_STAGE_WIDTH = 350;
    /**
     * Number of sprites per prestage per column.
     */
    public static final int NUMBER_OF_SPITE_PER_PRESTAGE_WIDTH = 70;
    /**
     * Number of pixel of the single sprite in the source image.
     */
    public static final int NUMBER_OF_PX_IN_IMAGE_PER_SPRITE = 8;
    /**
     * Sprite dimention in the final stage per pixel.
     */
    public static final int NUMBER_OF_PX_IN_MAP_PER_SPRITE = 16;
    /**
     * Number of stage.
     */
    public static final int STAGE_NUMBERS = 2;
    /**
     * Number of sprites rows.
     */
    public static final int MAX_IMAGE_HEIGHT = 2;
    /**
     * Number of sprites columns.
     */
    public static final int MAX_IMAGE_WIDTH = 17;
    /**
     * Classification of the type of sprite.
     */
    public static final List<LandPart> NAME_IMAGE_PART = Converter.getImageParts();
    /**
     * Relative url of the sprites image.
     */
    public static final String IMAGE_URL = "/shapes_trim.png";

    private static final int GREEN_SQUARE_STARTER_LOCATION = 5;

    private static final Map<LandPart, BufferedImage> SPRITE_MAP = fillMap();
    private static final Logger LOG = Logger.getLogger(LandUtils.class.getName());

    private LandUtils() {
    }

    private static Map<LandPart, BufferedImage> fillMap() {
        Map<LandPart, BufferedImage> ret = new HashMap<>();
        try {
            final BufferedImage spritesImage = ImageIO.read(LandUtils.class.getResource(LandUtils.IMAGE_URL));
            int i = 0;
            for (int y = 0; y < LandUtils.MAX_IMAGE_HEIGHT; y++) {
                for (int x = 0; x < LandUtils.MAX_IMAGE_WIDTH; x++) {
                    final LandPart part = LandUtils.NAME_IMAGE_PART.get(i);
                    ret.put(part, spritesImage.getSubimage(
                            LandUtils.NUMBER_OF_PX_IN_IMAGE_PER_SPRITE * x,
                            LandUtils.NUMBER_OF_PX_IN_IMAGE_PER_SPRITE * y,
                            LandUtils.NUMBER_OF_PX_IN_IMAGE_PER_SPRITE,
                            LandUtils.NUMBER_OF_PX_IN_IMAGE_PER_SPRITE));
                    i++;
                }
            }
            ret.put(LandUtils.NAME_IMAGE_PART.get(LandUtils.NAME_IMAGE_PART.size() - 1),
                    spritesImage.getSubimage(GREEN_SQUARE_STARTER_LOCATION, 0, 1, 1));

        } catch (IOException e) {
            LOG.severe("Ops!");
            LOG.severe(e.toString());
        }
        return ret;
    }

    /**
     * Method for the conversion of LandscapeSprite to the relative BufferedImage.
     * 
     * @param landScapeSprite
     * @return relative BufferedImage
     */
    public static BufferedImage getSprite(final LandPart landScapeSprite) {
        return SPRITE_MAP.get(landScapeSprite);
    }
}
