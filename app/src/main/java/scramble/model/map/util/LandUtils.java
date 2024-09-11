package scramble.model.map.util;

import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

import scramble.model.map.util.enums.LandPart;
import scramble.utility.Constants;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.logging.Logger;

import java.awt.image.BufferedImage;

/**
 * Class that contains all the methods and constants to manage the landscape
 * management.
 */
public final class LandUtils {

    /** Sprite dimention in the final stage per pixel. */
    public static final int PIXEL_PER_LAND_SPRITE_SIDE = 16;

    private static final int NUMBER_OF_PX_IN_IMAGE_PER_SPRITE = 8;
    private static final int MAX_IMAGE_HEIGHT = 2;
    private static final int MAX_IMAGE_WIDTH = 17;
    /**
     * Classification of the type of sprite.
     */
    public static final List<LandPart> NAME_IMAGE_PART = LandUtils.getImageParts();
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
        final Map<LandPart, BufferedImage> ret = new HashMap<>();
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
     * Multiply a number for the number of pixel per sprite's side.
     * @param x the number that needs to be multiply
     * @return the number multiplied by {@link Constants#PIXEL_PER_LAND_SPRITE_SIDE}
     */
    public static int multiplyPixelPerSprite(final int x) {
        return x * LandUtils.PIXEL_PER_LAND_SPRITE_SIDE;
    }

    /**
     * Divide a number for the number of pixel per sprite's side.
     * @param x the number that needs to be divided
     * @return the number divided by {@link Constants#PIXEL_PER_LAND_SPRITE_SIDE}
     */
    public static int dividePixelPerSprite(final int x) {
        return x / LandUtils.PIXEL_PER_LAND_SPRITE_SIDE;
    }

    /**
     * Add to a number the number of pixel per sprite's side.
     * @param x the number that needs to add the number of pixel per sprite's side
     * @return the number with {@link Constants#PIXEL_PER_LAND_SPRITE_SIDE} added to him
     */
    public static int addPixelPerSprite(final int x) {
        return x + LandUtils.PIXEL_PER_LAND_SPRITE_SIDE;
    }

    /**
     * Subtract to a number the number of pixel per sprite's side.
     * @param x the number that needs to subtract the number of pixel per sprite's side
     * @return the number with {@link Constants#PIXEL_PER_LAND_SPRITE_SIDE} subtracted to him
     */
    public static int subPixelPerSprite(final int x) {
        return x - LandUtils.PIXEL_PER_LAND_SPRITE_SIDE;
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

    /**
     * Method that insert all of the enum LandscapeSprite data into a List.
     * 
     * @return the list of all the enum LandscapeSprite
     */
    public static List<LandPart> getImageParts() {
        return Arrays.asList(LandPart.values());
    }
}
