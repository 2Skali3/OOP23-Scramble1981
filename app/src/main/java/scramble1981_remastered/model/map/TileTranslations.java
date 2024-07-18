package scramble1981_remastered.model.map;

import java.util.Map;
import java.util.HashMap;

public class TileTranslations {

    private Map<Integer, Integer> tileTranslations = new HashMap<>();

    public TileTranslations() {
        tileTranslations.put(1, 2);
        tileTranslations.put(2, 3);
        tileTranslations.put(4, 4);
        tileTranslations.put(16, 14);
        tileTranslations.put(44, 1);
        tileTranslations.put(45, 4);
        tileTranslations.put(46, 0);
        tileTranslations.put(47, 2);
        tileTranslations.put(48, 5);
        tileTranslations.put(49, 7);
        tileTranslations.put(50, 3);
        tileTranslations.put(51, 6);
        tileTranslations.put(52, 9);
        tileTranslations.put(53, 11);
        tileTranslations.put(54, 8);
        tileTranslations.put(58, 13);
        tileTranslations.put(92, 25);
        tileTranslations.put(93, 27);
        tileTranslations.put(94, 24);
        tileTranslations.put(96, 29);
        tileTranslations.put(97, 31);
        tileTranslations.put(98, 28);
        tileTranslations.put(99, 30);
        tileTranslations.put(209, 33);
        tileTranslations.put(31, 36);
        tileTranslations.put(27, 35);
        tileTranslations.put(17, 38);
        tileTranslations.put(30, 37);
        tileTranslations.put(25, 40);
        tileTranslations.put(29, 39);
    }

    public int getTileNum(int key) {
        return tileTranslations.get(key);
    }

}