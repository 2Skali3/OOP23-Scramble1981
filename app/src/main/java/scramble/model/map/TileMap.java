package scramble.model.map;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import java.util.logging.Logger;

/**
 * This class handles the tiles loading.
 * To every tile number obtained from the tile translation
 * is linked a tile file in the resources folder.
 */
public final class TileMap {
    private static final int NTILES = 40;
    private static final Logger LOG = Logger.getLogger(TileMap.class.getName());

    private final Map<Integer, BufferedImage> tiles;

    /**
     * Class constructor.
     */
    public TileMap() {
        tiles = new HashMap<>();
        loadTiles();
    }

    private void loadTiles() {
        for (int i = 0; i <= NTILES; i++) {
            try {
                tiles.put(i, ImageIO.read(getClass().getResource("/map/shapes/shapes_" + i + ".png")));
            } catch (IOException e) {
                LOG.severe("Ops!");
                LOG.severe(e.toString());
            }
        }
    }

    /**
     * Getter for the tile image.
     * 
     * @param tileName the tile name
     * @return the associated tile
     */
    public BufferedImage getTile(final int tileName) {
        return tiles.get(tileName);
    }

}
