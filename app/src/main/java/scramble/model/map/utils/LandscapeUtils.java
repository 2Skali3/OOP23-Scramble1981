package scramble.model.map.utils;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import javax.imageio.ImageIO;

import scramble.model.map.utils.LandscapePart.LandscapeSprite;

import java.awt.image.BufferedImage;

public class LandscapeUtils {
    public static final int NUMBER_OF_SPITE_PER_STAGE_HEIGHT = 40;
    public static final int NUMBER_OF_SPITE_PER_STAGE_WIDTH = 500;
    public static final int NUMBER_OF_SPITE_PER_PRESTAGE_WIDTH = 10; 
    public static final int NUMBER_OF_PX_IN_IMAGE_PER_SPRITE = 8;
    public static final int NUMBER_OF_PX_IN_MAP_PER_SPRITE = 16;
    public static final int STAGE_NUMBERS = 2;
    public static final int MAX_IMAGE_HEIGHT = 2; 
    public static final int MAX_IMAGE_WIDTH = 17;
    public static final List<LandscapeSprite> NAME_IMAGE_PART = LandscapePart.getImageParts();
    public static final String IMAGE_URL = "/shapes_trim.png";

    private Map<LandscapeSprite, BufferedImage> spritesMap;

    public LandscapeUtils(){
        this.fillMap();
    }

    private void fillMap(){
        
        this.spritesMap = new HashMap<>();
        try {
            BufferedImage spritesImage = ImageIO.read(getClass().getResource(LandscapeUtils.IMAGE_URL));
            for(int y = 0, i = 0; y < LandscapeUtils.MAX_IMAGE_HEIGHT; y++, i++) {
                for (int x = 0; x < LandscapeUtils.MAX_IMAGE_WIDTH; x++, i++) {
                    this.spritesMap.put(LandscapeUtils.NAME_IMAGE_PART.get(i), spritesImage.getSubimage(LandscapeUtils.NUMBER_OF_PX_IN_IMAGE_PER_SPRITE * x, LandscapeUtils.NUMBER_OF_PX_IN_IMAGE_PER_SPRITE * y, LandscapeUtils.NUMBER_OF_PX_IN_IMAGE_PER_SPRITE, LandscapeUtils.NUMBER_OF_PX_IN_IMAGE_PER_SPRITE));
                }
            }
            this.spritesMap.put(LandscapeUtils.NAME_IMAGE_PART.get(LandscapeUtils.NAME_IMAGE_PART.size() - 1), spritesImage.getSubimage(5, 0, 1, 1));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Metodo per la conversione di LandscapeSprite in BufferedImage
     * @param landScapeSprite; 
     * @return BufferedImage relativo al LandscapeSprite passato in input
     */
    public BufferedImage getSprite(LandscapeSprite landScapeSprite){
        return this.spritesMap.get(landScapeSprite);
    }
}
