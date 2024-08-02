package scramble.model.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.impl.MapElementImpl;
import scramble.model.map.impl.MapStageImpl;
import scramble.model.map.utils.LandscapeUtils;
import scramble.model.map.utils.LandscapePart.LandscapeSprite;
import scramble.model.map.utils.impl.CSVReaderImpl.Behaviour;
import scramble.model.map.utils.impl.CSVReaderImpl.StageComponent;

/**
 * Class for the generation of a MapStageImpl.
 */
public class MapStageGenerator {

    private static final LandscapeSprite[] FLAT_FLOOR_SPRITES = { LandscapeSprite.TOP_FLAT_FLOOR,
            LandscapeSprite.GORGE_FLOOR };
    private static final LandscapeSprite[] DOWN_FLOOR_SPRITES = { LandscapeSprite.CROWN_CLIMB,
            LandscapeSprite.STANDARD_CLIMB, LandscapeSprite.BOOT_CLIMB, LandscapeSprite.ROUND_CLIMB };
    private static final LandscapeSprite[] UP_FLOOR_SPRITES = { LandscapeSprite.MIRROR_CROWN_CLIMB,
            LandscapeSprite.MIRROR_STANDARD_CLIMB, LandscapeSprite.MIRROR_BOOT_CLIMB,
            LandscapeSprite.MIRROR_ROUND_CLIMB };
    private static final LandscapeSprite SUMMIT_FLOOR_SPRITE = LandscapeSprite.TRIANGLE_CLIFF;

    private static final LandscapeSprite[] FLAT_CEILING_SPRITES = { LandscapeSprite.FLAT_CEILING_DOWN,
            LandscapeSprite.GORGE_CEILING };
    private static final LandscapeSprite[] UP_CEILING_SPRITES = { LandscapeSprite.RIGHT_ROUND_CEILING,
            LandscapeSprite.RIGHT_CROWN_CEILING };
    private static final LandscapeSprite[] DOWN_CEILING_SPRITES = { LandscapeSprite.LEFT_ROUND_CEILING,
            LandscapeSprite.LEFT_ROUND_CEILING };
    private static final LandscapeSprite SUMMIT_CEILING_SPRITE = LandscapeSprite.STALACTITE_CEILING;

    private final int[] thresholdsFlat = { 95, 100 };
    private final int[] thresholdsUpDw = { 60, 80, 90, 100 };

    private LandscapeUtils landscapeUtils;

    /**
     * Constructor for MapStageGenerator.
     */
    public MapStageGenerator() {
        this.landscapeUtils = new LandscapeUtils();
    }

    private LandscapeSprite getSprite(final Behaviour behavior, final StageComponent stageComponent) {
        int[] thresholdsUpDown = thresholdsFlat;
        final ArrayList<LandscapeSprite> flatSprite;
        final ArrayList<LandscapeSprite> downSprite;
        final ArrayList<LandscapeSprite> upSprite;
        Random rand = new Random();
        int selectedItem;

        if (behavior == Behaviour.SUMMIT) {
            if (stageComponent == StageComponent.FLOOR) {
                return MapStageGenerator.SUMMIT_FLOOR_SPRITE;
            }
            return MapStageGenerator.SUMMIT_CEILING_SPRITE;
        }

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

        if (behavior == Behaviour.FLAT) {
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
                if (behavior == Behaviour.UP) {
                    return upSprite.get(i);
                }
                return downSprite.get(i);
            }
        }
        if (behavior == Behaviour.UP) {
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
            final PairImpl<ArrayList<PairImpl<Integer, Behaviour>>, ArrayList<PairImpl<Integer, Behaviour>>> rawDataCeilingAndFloor,
            final PairImpl<Integer, Integer> heightCeilingAndFloor, final int stageLength) {
        MapStageImpl returnStage = new MapStageImpl();
        int index = 0;
        PairImpl<Integer, Integer> tempX = new PairImpl<Integer, Integer>(0, 0);

        for (int x = 0; x < stageLength; x++) {
            if (rawDataCeilingAndFloor.getSecondElement().get(index).getSecondElement() == Behaviour.SUMMIT) {
                heightCeilingAndFloor.setSecondElement(heightCeilingAndFloor.getSecondElement() - 1);
            }
            returnStage.addColumn(new PairImpl<MapElementImpl, MapElementImpl>(
                    new MapElementImpl(heightCeilingAndFloor.getFirstElement(),
                            landscapeUtils.getSprite(LandscapeSprite.SKY)),
                    new MapElementImpl(
                            heightCeilingAndFloor.getSecondElement(),
                            landscapeUtils.getSprite(
                                    this.getSprite(
                                            rawDataCeilingAndFloor.getSecondElement().get(index).getSecondElement(),
                                            StageComponent.FLOOR)))));
            if (x - tempX.getSecondElement() == rawDataCeilingAndFloor.getSecondElement().get(index)
                    .getFirstElement()) {
                index++;
                tempX.setSecondElement(x);
                if (rawDataCeilingAndFloor.getSecondElement().get(index).getSecondElement() == Behaviour.DW) {
                    heightCeilingAndFloor.setSecondElement(heightCeilingAndFloor.getSecondElement() - 1);
                } else if (rawDataCeilingAndFloor.getSecondElement().get(index - 1)
                        .getSecondElement() == Behaviour.DW) {
                    heightCeilingAndFloor.setSecondElement(heightCeilingAndFloor.getSecondElement() + 1);
                }

                if (index - 1 >= 0
                        && rawDataCeilingAndFloor.getSecondElement().get(index - 1)
                                .getSecondElement() == Behaviour.SUMMIT) {
                    heightCeilingAndFloor.setSecondElement(heightCeilingAndFloor.getSecondElement() + 1);
                    System.out.println("Qui");
                }
            }

            if (rawDataCeilingAndFloor.getSecondElement().get(index).getSecondElement() == Behaviour.UP) {
                heightCeilingAndFloor.setSecondElement(heightCeilingAndFloor.getSecondElement() - 1);
            } else if (rawDataCeilingAndFloor.getSecondElement().get(index).getSecondElement() == Behaviour.DW) {
                heightCeilingAndFloor.setSecondElement(heightCeilingAndFloor.getSecondElement() + 1);
            }
        }
        return returnStage;
    }
}