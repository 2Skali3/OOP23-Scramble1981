package scramble1981_remastered.model.map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import scramble1981_remastered.model.map.LandScapePart.LandScapeSprite;

public class MapUtils {

    public static final int STAGE_HEIGHT = 40; // per pixel
    public static final int STAGE_WIDTH = 500; // per pixel

    public static final int IMAGE_SPRITE_PX = 8;
    public static final int MAP_SPRITE_PX = 16;
    
    public static final int STAGE_NUMBERS = 2;

    public static final int MAX_IMAGE_HEIGHT = 2; 
    public static final int MAX_IMAGE_WIDTH = 17;
    public static final List<LandScapeSprite> NAME_IMAGE_PART = LandScapePart.getImageParts();
    public static final String IMAGE_URL = "/shapes_trim.png";

    private Map<LandScapeSprite, BufferedImage> spritesMap;

    public MapUtils(){
        this.fillMap();
    }

    private void fillMap(){

        // BufferedImage img = ImageIO.read(new File("graphic.png"));

        this.spritesMap = new HashMap<>();
        try {

            BufferedImage spritesImage = ImageIO.read(getClass().getResource(MapUtils.IMAGE_URL)); // getClass().getResource(MapUtils.IMAGE_URL)
            for(int y = 0, i = 0; y < MapUtils.MAX_IMAGE_HEIGHT; y++, i++) {
                for (int x = 0; x < MapUtils.MAX_IMAGE_WIDTH; x++, i++) {
                    this.spritesMap.put(MapUtils.NAME_IMAGE_PART.get(i), spritesImage.getSubimage(MapUtils.IMAGE_SPRITE_PX * x, MapUtils.IMAGE_SPRITE_PX * y, MapUtils.IMAGE_SPRITE_PX, MapUtils.IMAGE_SPRITE_PX));
                }
            }

            this.spritesMap.put(MapUtils.NAME_IMAGE_PART.get(MapUtils.NAME_IMAGE_PART.size() - 1), spritesImage.getSubimage(5, 0, 1, 1));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public BufferedImage getSprite(LandScapeSprite landScapeSprite){
        return this.spritesMap.get(landScapeSprite);
    }

}
