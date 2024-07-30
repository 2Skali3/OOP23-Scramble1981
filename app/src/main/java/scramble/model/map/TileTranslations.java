package scramble.model.map;

import java.util.Map;
import java.util.HashMap;

/**
 * This class handles the tile translation.
 * To every number obtained from decoding the landscape data
 * is linked a tile file that goes to 0 to 40.
 */
public class TileTranslations {

    private final Map<Integer, Integer> tileTranslate = new HashMap<>();

    /** Class constructor. */
    public TileTranslations() {
        tileTranslate.put(TileTranslationEnum.TILE_2A.getDecodedValue(), TileTranslationEnum.TILE_2A.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_3A.getDecodedValue(), TileTranslationEnum.TILE_3A.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_4A.getDecodedValue(), TileTranslationEnum.TILE_4A.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_14.getDecodedValue(), TileTranslationEnum.TILE_14.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_1.getDecodedValue(), TileTranslationEnum.TILE_1.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_4B.getDecodedValue(), TileTranslationEnum.TILE_4B.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_0.getDecodedValue(), TileTranslationEnum.TILE_0.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_2B.getDecodedValue(), TileTranslationEnum.TILE_2B.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_5.getDecodedValue(), TileTranslationEnum.TILE_5.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_7.getDecodedValue(), TileTranslationEnum.TILE_7.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_3B.getDecodedValue(), TileTranslationEnum.TILE_3B.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_6.getDecodedValue(), TileTranslationEnum.TILE_6.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_9.getDecodedValue(), TileTranslationEnum.TILE_9.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_11.getDecodedValue(), TileTranslationEnum.TILE_11.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_8.getDecodedValue(), TileTranslationEnum.TILE_8.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_13.getDecodedValue(), TileTranslationEnum.TILE_13.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_25.getDecodedValue(), TileTranslationEnum.TILE_25.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_27.getDecodedValue(), TileTranslationEnum.TILE_27.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_24.getDecodedValue(), TileTranslationEnum.TILE_24.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_29.getDecodedValue(), TileTranslationEnum.TILE_29.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_31.getDecodedValue(), TileTranslationEnum.TILE_31.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_28.getDecodedValue(), TileTranslationEnum.TILE_28.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_30.getDecodedValue(), TileTranslationEnum.TILE_30.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_33.getDecodedValue(), TileTranslationEnum.TILE_33.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_36.getDecodedValue(), TileTranslationEnum.TILE_36.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_35.getDecodedValue(), TileTranslationEnum.TILE_35.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_38.getDecodedValue(), TileTranslationEnum.TILE_38.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_37.getDecodedValue(), TileTranslationEnum.TILE_37.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_40.getDecodedValue(), TileTranslationEnum.TILE_40.getIndex());
        tileTranslate.put(TileTranslationEnum.TILE_39.getDecodedValue(), TileTranslationEnum.TILE_39.getIndex());
    }

    /**
     * Getter for the tile number.
     * 
     * @param key the map key
     * @return yhe tile number
     */
    public int getTileNum(final int key) {
        return tileTranslate.get(key);
    }

}
