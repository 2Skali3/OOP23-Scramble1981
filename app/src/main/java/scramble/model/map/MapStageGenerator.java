package scramble.model.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.impl.MapElementImpl;
import scramble.model.map.impl.MapStageImpl;
import scramble.model.map.utils.LandscapeUtils;
import scramble.model.map.utils.enums.LandscapePart;
import scramble.model.map.utils.enums.LandscapeBehaviour;
import scramble.model.map.utils.enums.StageComponent;

/**
 * Class for the generation of a MapStageImpl.
 */
public class MapStageGenerator {

    private static final LandscapePart[] FLAT_FLOOR_SPRITES = { LandscapePart.TOP_FLAT_FLOOR,
            LandscapePart.GORGE_FLOOR };
    private static final LandscapePart[] DOWN_FLOOR_SPRITES = { LandscapePart.CROWN_CLIMB, LandscapePart.STANDARD_CLIMB,
            LandscapePart.BOOT_CLIMB, LandscapePart.ROUND_CLIMB };
    private static final LandscapePart[] UP_FLOOR_SPRITES = { LandscapePart.MIRROR_CROWN_CLIMB,
            LandscapePart.MIRROR_STANDARD_CLIMB, LandscapePart.MIRROR_BOOT_CLIMB, LandscapePart.MIRROR_ROUND_CLIMB };
    private static final LandscapePart SUMMIT_FLOOR_SPRITE = LandscapePart.TRIANGLE_CLIFF;

    private static final LandscapePart[] FLAT_CEILING_SPRITES = { LandscapePart.FLAT_CEILING_DOWN,
            LandscapePart.GORGE_CEILING };
    private static final LandscapePart[] UP_CEILING_SPRITES = { LandscapePart.RIGHT_ROUND_CEILING,
            LandscapePart.RIGHT_CROWN_CEILING };
    private static final LandscapePart[] DOWN_CEILING_SPRITES = { LandscapePart.LEFT_ROUND_CEILING,
            LandscapePart.LEFT_ROUND_CEILING };
    private static final LandscapePart SUMMIT_CEILING_SPRITE = LandscapePart.STALACTITE_CEILING;

    private final Random rand;

    private final int[] thresholdsFlat = { 95, 100 };
    private final int[] thresholdsUpDw = { 50, 60, 90, 100 };

    private final LandscapeUtils landscapeUtils;

    /**
     * Constructor for MapStageGenerator.
     */
    public MapStageGenerator() {
        this.landscapeUtils = new LandscapeUtils();
        this.rand = new Random();
    }

    private LandscapePart getSprite(final LandscapeBehaviour behavior, final StageComponent stageComponent) {

        if (behavior == LandscapeBehaviour.SUMMIT) {
            if (stageComponent == StageComponent.FLOOR) {
                return MapStageGenerator.SUMMIT_FLOOR_SPRITE;
            }
            return MapStageGenerator.SUMMIT_CEILING_SPRITE;
        }

        final ArrayList<LandscapePart> flatSprite;
        final ArrayList<LandscapePart> downSprite;
        final ArrayList<LandscapePart> upSprite;
        int[] thresholdsUpDown = thresholdsUpDw;

        if (stageComponent == StageComponent.FLOOR) {
            flatSprite = new ArrayList<>(Arrays.asList(MapStageGenerator.FLAT_FLOOR_SPRITES));
            downSprite = new ArrayList<>(Arrays.asList(MapStageGenerator.DOWN_FLOOR_SPRITES));
            upSprite = new ArrayList<>(Arrays.asList(MapStageGenerator.UP_FLOOR_SPRITES));
        } else {
            flatSprite = new ArrayList<>(Arrays.asList(MapStageGenerator.FLAT_CEILING_SPRITES));
            downSprite = new ArrayList<>(Arrays.asList(MapStageGenerator.DOWN_CEILING_SPRITES));
            upSprite = new ArrayList<>(Arrays.asList(MapStageGenerator.UP_CEILING_SPRITES));
            thresholdsUpDown = thresholdsFlat;
        }
        int selectedItem;
        if (behavior == LandscapeBehaviour.FLAT) {
            selectedItem = rand.nextInt(thresholdsFlat[thresholdsFlat.length - 1]);

            for (int i = 0; i < thresholdsFlat.length - 1; i++) {
                if (selectedItem < thresholdsFlat[i]) {
                    return flatSprite.get(i);
                }
            }
            return flatSprite.get(thresholdsFlat.length - 1);
        }
        selectedItem = rand.nextInt(thresholdsUpDown[thresholdsUpDown.length - 1]);
        for (int i = 0; i < thresholdsUpDown.length; i++) {
            if (selectedItem < thresholdsUpDown[i]) {
                if (behavior == LandscapeBehaviour.UP) {
                    return upSprite.get(i);
                }
                return downSprite.get(i);
            }
        }
        if (behavior == LandscapeBehaviour.UP) {
            return upSprite.get(thresholdsUpDw.length - 1);
        }
        return downSprite.get(thresholdsUpDw.length - 1);

    }

    /**
     * Test.
     * 
     * @param rawDataCeilingAndFloor
     * @param heightCeilingAndFloor
     * @param stageLength
     * @return esatto
     */
    public MapStageImpl convertDataToMapStage(
            final PairImpl<
                List<PairImpl<Integer, LandscapeBehaviour>>, 
                List<PairImpl<Integer, LandscapeBehaviour>>> rawDataCeilingAndFloor,
            final PairImpl<Integer, Integer> heightCeilingAndFloor, final int stageLength) {
        int index = 0;
        final PairImpl<Integer, Integer> tempX = new PairImpl<>(0, 0);
        final MapStageImpl returnStage = new MapStageImpl();
        for (int x = 0; x < stageLength; x++) {
            if (rawDataCeilingAndFloor.getSecondElement().get(index).getSecondElement() == LandscapeBehaviour.SUMMIT) {
                heightCeilingAndFloor.setSecondElement(heightCeilingAndFloor.getSecondElement() - 1);
            }
            returnStage.addColumn(new PairImpl<MapElementImpl, MapElementImpl>(
                    new MapElementImpl(heightCeilingAndFloor.getFirstElement(),
                            landscapeUtils.getSprite(LandscapePart.SKY)),
                    new MapElementImpl(heightCeilingAndFloor.getSecondElement(),
                            landscapeUtils.getSprite(this.getSprite(
                                    rawDataCeilingAndFloor.getSecondElement().get(index).getSecondElement(),
                                    StageComponent.FLOOR)))));
            if (x - tempX.getSecondElement() == rawDataCeilingAndFloor.getSecondElement().get(index)
                    .getFirstElement()) {
                index++;
                tempX.setSecondElement(x);
                if (rawDataCeilingAndFloor.getSecondElement().get(index).getSecondElement() == LandscapeBehaviour.DW) {
                    heightCeilingAndFloor.setSecondElement(heightCeilingAndFloor.getSecondElement() - 1);
                } else if (rawDataCeilingAndFloor.getSecondElement().get(index - 1)
                        .getSecondElement() == LandscapeBehaviour.DW) {
                    heightCeilingAndFloor.setSecondElement(heightCeilingAndFloor.getSecondElement() + 1);
                }

                if (index - 1 >= 0 && rawDataCeilingAndFloor.getSecondElement().get(index - 1)
                        .getSecondElement() == LandscapeBehaviour.SUMMIT) {
                    heightCeilingAndFloor.setSecondElement(heightCeilingAndFloor.getSecondElement() + 1);
                }
            }

            if (rawDataCeilingAndFloor.getSecondElement().get(index).getSecondElement() == LandscapeBehaviour.UP) {
                heightCeilingAndFloor.setSecondElement(heightCeilingAndFloor.getSecondElement() - 1);
            } else if (rawDataCeilingAndFloor.getSecondElement().get(index)
                    .getSecondElement() == LandscapeBehaviour.DW) {
                heightCeilingAndFloor.setSecondElement(heightCeilingAndFloor.getSecondElement() + 1);
            }
        }
        return returnStage;
    }
}
