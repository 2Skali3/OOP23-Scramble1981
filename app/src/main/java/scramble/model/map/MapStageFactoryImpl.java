package scramble.model.map;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.api.MapStageFactory;
import scramble.model.map.api.MapStage;
import scramble.model.map.utils.LandscapeDataLoads;
import scramble.model.map.utils.LandscapeUtils;

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

    private final MapStageGenerator mapStageGenerator;
    private final PairImpl<Integer, Integer> heightCeilingAndFloor;

    /**
     * Constructor of the class MapStageFactory.
     */
    public MapStageFactoryImpl() {
        this.heightCeilingAndFloor = new PairImpl<>(MapStageFactoryImpl.STARTER_CEILING_HEIGHT,
                MapStageFactoryImpl.STARTER_FLOOR_HEIGHT);
        this.mapStageGenerator = new MapStageGenerator();
    }

    /**
     * @inheritDoc
     */
    @Override
    public MapStage prestage() {
        return mapStageGenerator.convertDataToMapStage(LandscapeDataLoads.getPrestageData(), this.heightCeilingAndFloor,
                LandscapeUtils.NUMBER_OF_SPITE_PER_PRESTAGE_WIDTH);
    }

    /**
     * @inheritDoc
     */
    @Override
    public MapStage stage1() {
        return mapStageGenerator.convertDataToMapStage(LandscapeDataLoads.getStage1Data(), this.heightCeilingAndFloor,
                LandscapeUtils.NUMBER_OF_SPITE_PER_STAGE_WIDTH);
    }

    /**
     * @inheritDoc
     */
    @Override
    public MapStage stage2() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stage2'");
    }

    /**
     * @inheritDoc
     */
    @Override
    public MapStage stage3() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stage3'");
    }

    /**
     * @inheritDoc
     */
    @Override
    public MapStage stage4() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stage4'");
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
