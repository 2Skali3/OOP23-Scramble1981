package scramble.model.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class handles the decoding of the LandscapeData class in the same
 * package.
 * Every stage is passed through like in the original version of the game.
 * From the stage lists the map for top and bottom landscaped is manufactured.
 */
public class LevelsBuilder {

    private static final int HEXDECODE = 248;
    private static final int PTR_BOTTOM = 6;
    private static final int PTR_CEILING = 9;
    private static final int INNER_TILE_FULL = 15;
    private static final int INNER_TILE_SQR = 17;
    private static final int HEXADIVIDE = 8;
    private static final int INNER_TILE_FINAL = 34;
    private static final int ADVANCE5 = 5;
    private static final int ADVANCE7 = 7;
    private static final int STAGE_END = 255;
    private static final int FINAL_STAGE = 6;
    private static final String TOP = "top";
    private static final String BOTTOM = "bottom";

    private int stage = 1;
    private final TileTranslations tileTranslations;

    /**
     * Class constructor.
     * It initialises the tile map for decoding from hexa to a tile decimal number.
     */
    public LevelsBuilder() {
        tileTranslations = new TileTranslations();
    }

    /**
     * The core of the class, loops through the all stages data and builds up the
     * relative game map.
     * 
     * @param stageData the whole stage data
     * @return the decoded landscape
     */
    public Map<String, List<Map<String, Object>>> decodeLandscape(final List<String> stageData) {
        /* legge e decodifica tutto lo stage */
        final List<Map<String, Object>> landscapeBottomData = new ArrayList<>();
        final List<Map<String, Object>> landscapeTopData = new ArrayList<>();
        final Map<String, List<Map<String, Object>>> landscapeData = new HashMap<>();

        int pointer = 0;

        while (pointer < stageData.size()) {

            if (!isEndOfStage(stageData.get(pointer))) {

                final int firstCharPtr = (Integer.decode(stageData.get(pointer)) & HEXDECODE) / HEXADIVIDE;
                final int firstChar = tileTranslations.getTileNum(Integer.decode(stageData.get(pointer + 1)));
                final int secondCharPtr = (Integer.decode(stageData.get(pointer + 2)) & HEXDECODE) / HEXADIVIDE;
                final int secondChar = tileTranslations.getTileNum(Integer.decode(stageData.get(pointer + 3)));

                // int groundObjectId = Integer.decode(stageData.get(pointer + 5));

                final Map<String, Object> bottomSegment1 = createSegment(firstCharPtr, firstChar,
                        stage > 3 ? INNER_TILE_SQR : INNER_TILE_FULL,
                        BOTTOM);
                final Map<String, Object> bottomSegment2 = createSegment(secondCharPtr, secondChar,
                        stage > 3 ? INNER_TILE_FINAL : INNER_TILE_FULL,
                        BOTTOM);

                landscapeBottomData.add(bottomSegment1);
                landscapeBottomData.add(bottomSegment2);

                final boolean hasCeiling = Integer.decode(stageData.get(pointer + 4)) != 0;
                if (hasCeiling) {
                    final int ceilingFirstCharPtr = (Integer.decode(stageData.get(pointer + 4)) & HEXDECODE)
                            / HEXADIVIDE;
                    final int ceilingFirstChar = tileTranslations
                            .getTileNum(Integer.decode(stageData.get(pointer + ADVANCE5)));
                    final int ceilingSecondCharPtr = (Integer.decode(stageData.get(pointer + PTR_BOTTOM)) & HEXDECODE)
                            / HEXADIVIDE;
                    final int ceilingSecondChar = tileTranslations
                            .getTileNum(Integer.decode(stageData.get(pointer + ADVANCE7)));

                    final Map<String, Object> topSegment1 = createSegment(ceilingFirstCharPtr, ceilingFirstChar,
                            stage > 3 ? INNER_TILE_SQR : INNER_TILE_FULL, TOP);
                    final Map<String, Object> topSegment2 = createSegment(ceilingSecondCharPtr, ceilingSecondChar,
                            stage > 3 ? INNER_TILE_FINAL : INNER_TILE_FULL, TOP);
                    landscapeTopData.add(topSegment1);
                    landscapeTopData.add(topSegment2);

                    pointer += PTR_CEILING;
                } else {
                    final Map<String, Object> topSegment1 = createSegment(-1, 0,
                            stage > 3 ? INNER_TILE_SQR : INNER_TILE_FULL,
                            TOP);
                    final Map<String, Object> topSegment2 = createSegment(-1, 0,
                            stage > 3 ? INNER_TILE_SQR : INNER_TILE_FULL,
                            TOP);
                    landscapeTopData.add(topSegment1);
                    landscapeTopData.add(topSegment2);
                    pointer += PTR_BOTTOM;
                }
            } else {
                if (stage != FINAL_STAGE) {
                    stage++;
                    pointer++;
                } else {
                    break;
                }
            }

        }

        landscapeData.put("landscape_top", landscapeTopData);
        landscapeData.put("landscape_bottom", landscapeBottomData);
        return landscapeData;
    }

    /**
     * Checks if pointer is at the end of one stage (0xFF).
     * 
     * @param data an hexa number
     * @return true or false depending on the aforementioned
     */
    private boolean isEndOfStage(final String data) {
        return Integer.decode(data) == STAGE_END;
    }

    /**
     * Checks if pointer is at the end of one stage (0xFF).
     * 
     * @param ypos     position on the y axis where to draw sprite
     * @param tileId   surface sprite Id
     * @param inner    sprite Id for the filling
     * @param position top or bottom
     * @return segment to add to the final map
     */
    private Map<String, Object> createSegment(final int ypos, final int tileId, final int inner,
            final String position) {
        final Map<String, Object> segment = new HashMap<>();
        segment.put("ypos", ypos);
        segment.put("outer_tile_id", tileId);
        segment.put("inner_tile_id", inner);
        segment.put("position", position);
        return segment;
    }

}
