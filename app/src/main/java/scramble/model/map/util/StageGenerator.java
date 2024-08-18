package scramble.model.map.util;

import java.util.ArrayList;
import java.util.List;

import scramble.model.common.api.Pair;
import scramble.model.common.impl.PairImpl;
import scramble.model.map.api.MapStage;
import scramble.model.map.impl.MapElement;
import scramble.model.map.impl.MapStageImpl;
import scramble.model.map.util.enums.LandBehaviour;
import scramble.model.map.util.enums.LandPart;
// import scramble.model.map.util.enums.LandType;
import scramble.model.map.util.enums.StagePart;
import scramble.model.map.util.land.greenland.raw.RawData;
import scramble.model.map.util.land.greenland.raw.SegmentRawData;

import java.util.Random;
import java.util.Arrays;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Class for the generation of a MapStage.
 */
public class StageGenerator {

    private Pair<Integer, Integer> currentYCeilingAndFloor;

    private static final LandPart[] FLAT = { LandPart.TOP_FLAT_FLOOR,
            LandPart.GORGE_FLOOR };
    private static final LandPart[] DOWN = { LandPart.CROWN_CLIMB, LandPart.STANDARD_CLIMB,
            LandPart.BOOT_CLIMB, LandPart.ROUND_CLIMB };
    private static final LandPart[] UP = { LandPart.MIRROR_CROWN_CLIMB,
            LandPart.MIRROR_STANDARD_CLIMB, LandPart.MIRROR_BOOT_CLIMB, LandPart.MIRROR_ROUND_CLIMB };
    private static final LandPart SUMMIT = LandPart.TRIANGLE_CLIFF;

    private final Random rand;

    private static final double ANCHOR_X = 2;
    private static final double ANCHOR_Y = 2;

    private final int[] thresholdsFlat = { 95, 100 };
    private final int[] thresholdsUpDw = { 50, 60, 90, 100 };

    /**
     * Constructor for MapStageGenerator.
     * 
     * @param heightCeilingAndFloor contains the height of the ceiling as the first
     *                              element and the height of the floor as a second
     *                              element
     */
    public StageGenerator(final Pair<Integer, Integer> heightCeilingAndFloor) {
        this.currentYCeilingAndFloor = new PairImpl<Integer, Integer>(heightCeilingAndFloor.getFirstElement(),
                heightCeilingAndFloor.getSecondElement());
        this.rand = new Random();
    }

    private int applyPxInMap(final int n) {
        return n * LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE;
    }

    private LandPart getSprite(final LandBehaviour behavior, final StagePart stageComponent) {

        if (behavior == LandBehaviour.SUMMIT) {
            return StageGenerator.SUMMIT;
        }

        final ArrayList<LandPart> flatSprite;
        final ArrayList<LandPart> downSprite;
        final ArrayList<LandPart> upSprite;
        int[] thresholdsUpDown = thresholdsUpDw;

        flatSprite = new ArrayList<>(Arrays.asList(StageGenerator.FLAT));
        downSprite = new ArrayList<>(Arrays.asList(StageGenerator.DOWN));
        upSprite = new ArrayList<>(Arrays.asList(StageGenerator.UP));

        int selectedItem;
        if (behavior == LandBehaviour.FLAT) {
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
                if (behavior == LandBehaviour.UP) {
                    return upSprite.get(i);
                }
                return downSprite.get(i);
            }
        }
        if (behavior == LandBehaviour.UP) {
            return upSprite.get(thresholdsUpDw.length - 1);
        }
        return downSprite.get(thresholdsUpDw.length - 1);

    }

    private static BufferedImage flipBufferedImageWithDegree(final BufferedImage toModify, final int degrees) {
        final BufferedImage modifiedImage = new BufferedImage(toModify.getWidth(), toModify.getHeight(),
                toModify.getType());
        final Graphics2D g2d = modifiedImage.createGraphics();
        final AffineTransform transform = new AffineTransform();

        transform.rotate(Math.toRadians(degrees), toModify.getWidth() / ANCHOR_X, toModify.getHeight() / ANCHOR_Y);
        g2d.setTransform(transform);
        g2d.drawImage(toModify, 0, 0, null);
        g2d.dispose();

        return modifiedImage;
    }

    private Pair<List<MapElement>, Integer> elaborateData(final StagePart stageComponent,
            final List<SegmentRawData> rawData, final int stageLenght, final int y) {
        final int incY;
        if (stageComponent == StagePart.CEILING) {
            incY = 0;
        } else {
            incY = 1;
        }

        List<MapElement> elaboratedData = new ArrayList<>();

        int index = 0;
        int length = rawData.get(index).getLength();
        LandBehaviour behaviour = rawData.get(index).getBehaviour();
        LandBehaviour prevBehaviour;
        // LandType landType = rawData.get(index).getLandType(); // variabile che tiene
        // traccia del tipo di terreno da applicare
        int currentY = y;
        for (int x = 0; x < stageLenght; x++) {

            if (behaviour == LandBehaviour.SUMMIT) {
                currentY = currentY - incY;
            }

            BufferedImage bi = LandUtils.getSprite(this.getSprite(behaviour, stageComponent));
            if (StagePart.CEILING == stageComponent) {
                bi = flipBufferedImageWithDegree(bi, 180);
            }

            if (behaviour != LandBehaviour.EMPTY) {
                elaboratedData.add(new MapElement(
                        this.applyPxInMap(x), this.applyPxInMap(currentY),
                        LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE,
                        LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE,
                        bi));
            }

            if (x == length) {
                index++;
                prevBehaviour = behaviour;
                behaviour = rawData.get(index).getBehaviour();
                // landType = rawData.get(index).getLandType();
                length = rawData.get(index).getLength() + x;
                currentY = updateCurrentY(behaviour, prevBehaviour, stageComponent, currentY);
            }

            if (behaviour == LandBehaviour.UP) {
                currentY -= 1;
            } else if (behaviour == LandBehaviour.DW) {
                currentY += 1;
            }
        }

        return new PairImpl<>(elaboratedData, currentY);
    }

    private int updateCurrentY(final LandBehaviour behaviour, final LandBehaviour prevBehaviour,
            final StagePart stageComponent, final int y) {
        final LandBehaviour triggerBehaviour;
        final int incY;
        if (stageComponent == StagePart.CEILING) {
            triggerBehaviour = LandBehaviour.UP;
            incY = -1;
        } else {
            triggerBehaviour = LandBehaviour.DW;
            incY = 1;
        }

        int currentY = y;
        if (behaviour == triggerBehaviour) {
            currentY -= incY;
        } else if (behaviour == LandBehaviour.SUMMIT && stageComponent == StagePart.CEILING) {
            currentY += 1;
        }
        if (prevBehaviour == triggerBehaviour) {
            currentY += incY;
        } else if (prevBehaviour == LandBehaviour.SUMMIT) {
            currentY += incY;
        }

        return currentY;
    }

    /**
     * Method that takes raw data relative to a specific map stage and elaborates
     * them.
     * 
     * @param rawData     the raw data relative to the map stage
     * @param stageLength the length of the stage
     * @return the elaborated data as a {@link MapStage}
     * @see MapStage
     * @see RawData
     */
    public MapStage convertDataToMapStage(final RawData rawData, final int stageLength) {
        final MapStage elaboratedStage = new MapStageImpl();

        Pair<List<MapElement>, Integer> elaboratedData;
        elaboratedData = this.elaborateData(StagePart.CEILING, rawData.getCeiling(), stageLength,
                currentYCeilingAndFloor.getFirstElement());
        currentYCeilingAndFloor.setFirstElement(elaboratedData.getSecondElement());
        elaboratedStage.setCeiling(elaboratedData.getFirstElement());

        elaboratedData = this.elaborateData(StagePart.FLOOR, rawData.getFloor(), stageLength,
                currentYCeilingAndFloor.getSecondElement());
        currentYCeilingAndFloor.setSecondElement(elaboratedData.getSecondElement());
        elaboratedStage.setFloor(elaboratedData.getFirstElement());

        return elaboratedStage;
    }
}
