package scramble1981_remastered.model.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelsBuilder {

    private int stage = 1;
    private TileTranslations tileTranslations;

    public LevelsBuilder() {
        tileTranslations = new TileTranslations();
    }

    public Map<String, List<Map<String, Object>>> decodeLandscape(List<String> stageData) {
        /* legge e decodifica tutto lo stage */
        List<Map<String, Object>> landscapeBottomData = new ArrayList<>();
        List<Map<String, Object>> landscapeTopData = new ArrayList<>();
        Map<String, List<Map<String, Object>>> landscapeData = new HashMap<>();

        int pointer = 0;

        while (pointer < stageData.size()) {

            if (!isEndOfStage(stageData.get(pointer))) {

                int firstCharPtr = (Integer.decode(stageData.get(pointer)) & 248) / 8;
                int firstChar = tileTranslations.getTileNum(Integer.decode(stageData.get(pointer + 1)));
                int secondCharPtr = (Integer.decode(stageData.get(pointer + 2)) & 248) / 8;
                int secondChar = tileTranslations.getTileNum(Integer.decode(stageData.get(pointer + 3)));

                // int groundObjectId = Integer.decode(stageData.get(pointer + 5));

                Map<String, Object> bottomSegment1 = createSegment(firstCharPtr, firstChar, stage > 3 ? 17 : 15,
                        "bottom");
                Map<String, Object> bottomSegment2 = createSegment(secondCharPtr, secondChar, stage > 3 ? 34 : 15,
                        "bottom");

                landscapeBottomData.add(bottomSegment1);
                landscapeBottomData.add(bottomSegment2);

                boolean hasCeiling = Integer.decode(stageData.get(pointer + 4)) != 0;
                if (hasCeiling) {
                    int ceilingFirstCharPtr = (Integer.decode(stageData.get(pointer + 4)) & 248) / 8;
                    int ceilingFirstChar = tileTranslations.getTileNum(Integer.decode(stageData.get(pointer + 5)));
                    int ceilingSecondCharPtr = (Integer.decode(stageData.get(pointer + 6)) & 248) / 8;
                    int ceilingSecondChar = tileTranslations.getTileNum(Integer.decode(stageData.get(pointer + 7)));

                    Map<String, Object> topSegment1 = createSegment(ceilingFirstCharPtr, ceilingFirstChar,
                            stage > 3 ? 17 : 15, "top");
                    Map<String, Object> topSegment2 = createSegment(ceilingSecondCharPtr, ceilingSecondChar,
                            stage > 3 ? 34 : 15, "top");
                    landscapeTopData.add(topSegment1);
                    landscapeTopData.add(topSegment2);

                    pointer += 9;
                } else {
                    Map<String, Object> topSegment1 = createSegment(-1, 0, stage > 3 ? 17 : 15, "top");
                    Map<String, Object> topSegment2 = createSegment(-1, 0, stage > 3 ? 17 : 15, "top");
                    landscapeTopData.add(topSegment1);
                    landscapeTopData.add(topSegment2);
                    pointer += 6;
                }
            } else {
                if (stage != 6) {
                    System.out.println(stage);
                    stage++;
                    pointer++;
                } else
                    break;
            }

        }

        landscapeData.put("landscape_top", landscapeTopData);
        landscapeData.put("landscape_bottom", landscapeBottomData);
        return landscapeData;
    }

    private boolean isEndOfStage(String data) {
        return Integer.decode(data) == 255;
    }

    private Map<String, Object> createSegment(int ypos, int tileId, int inner, String position) {
        Map<String, Object> segment = new HashMap<>();
        segment.put("ypos", ypos);
        segment.put("outer_tile_id", tileId);
        segment.put("inner_tile_id", inner);
        segment.put("position", position);
        return segment;
    }

}
