package scramble1981_remastered.model.map;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class TileMap {
    private Map<Integer, BufferedImage> tiles;

    public TileMap() {
        tiles = new HashMap<>();
        loadTiles();
    }

    private void loadTiles() {
        for (int i = 0; i <= 40; i++) {
            try {
                tiles.put(i, ImageIO.read(getClass().getResource("/map/shapes_" + i + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public BufferedImage getTile(int tileName) {
        return tiles.get(tileName);
    }

}
