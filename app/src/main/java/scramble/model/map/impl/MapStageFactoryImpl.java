package scramble.model.map.impl;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.api.MapStage;
import scramble.model.map.api.MapStageFactory;
import scramble.model.map.util.LandUtils;
import scramble.model.map.util.LandsDataLoader;
import scramble.model.map.util.StageGenerator;

/**
 * Implementation of the interface MapStageFactory.
 * 
 * @see MapStageFactory
 */
public class MapStageFactoryImpl implements MapStageFactory {
    /**
     * the starter height value of the ceiling.
     */
    public static final int STARTER_CEILING_HEIGHT = -1;
    /**
     * the starter height value of the floor.
     */
    public static final int STARTER_FLOOR_HEIGHT = 35;

    private final StageGenerator mapStageGenerator;

    /**
     * Constructor of the class MapStageFactory.
     */
    public MapStageFactoryImpl() {
        this.mapStageGenerator = new StageGenerator(new PairImpl<Integer, Integer>(
                MapStageFactoryImpl.STARTER_CEILING_HEIGHT, MapStageFactoryImpl.STARTER_FLOOR_HEIGHT));
    }

    /**
     * @inheritDoc
     */
    @Override
    public MapStage prestage() {
        return mapStageGenerator.convertDataToMapStage(LandsDataLoader.getPrestageData(),
                LandUtils.NUMBER_OF_SPITE_PER_PRESTAGE_WIDTH);
    }

    /**
     * @inheritDoc
     */
    @Override
    public MapStage stage1() {
        return mapStageGenerator.convertDataToMapStage(LandsDataLoader.getStage1Data(),
                LandUtils.NUMBER_OF_SPITE_PER_STAGE_WIDTH);
    }

    /**
     * @inheritDoc
     */
    @Override
    public MapStage stage2() {
        return mapStageGenerator.convertDataToMapStage(LandsDataLoader.getStage2Data(),
                LandUtils.NUMBER_OF_SPITE_PER_STAGE_WIDTH);
    }

    /**
     * @inheritDoc
     */
    @Override
    public MapStage stage3() {
        return mapStageGenerator.convertDataToMapStage(LandsDataLoader.getStage3Data(),
                LandUtils.NUMBER_OF_SPITE_PER_STAGE_WIDTH);
    }

    /**
     * @inheritDoc
     */
    @Override
    public MapStage stage4() {
        return mapStageGenerator.convertDataToMapStage(LandsDataLoader.getStage4Data(),
                LandUtils.NUMBER_OF_SPITE_PER_STAGE_WIDTH);
    }

    /**
     * @inheritDoc
     */
    @Override
    public MapStage stage5() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stage5'");
    }

    /**
     * @inheritDoc
     */
    @Override
    public MapStage stage6() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stage6'");
    }
}
