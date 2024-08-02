package scramble.model.map;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.api.MapStageFactory;
import scramble.model.map.impl.MapStageImpl;
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
    public static final int STARTER_CEILINF_HEIGHT = -1;
    /**
     * the starter height value of the floor.
     */
    public static final int STARTER_FLOOR_HEIGHT = 35;

    private MapStageGenerator mapStageGenerator;
    private PairImpl<Integer, Integer> heightCeilingAndFloor;

    /**
     * Constructor of the class MapStageFactory.
     */
    public MapStageFactoryImpl() {
        this.heightCeilingAndFloor = new PairImpl<Integer, Integer>(
                MapStageFactoryImpl.STARTER_CEILINF_HEIGHT,
                MapStageFactoryImpl.STARTER_FLOOR_HEIGHT);
        this.mapStageGenerator = new MapStageGenerator();
    }

    /**
     * @inheritDoc
     */
    @Override
    public MapStageImpl prestage() {
        return mapStageGenerator.convertDataToMapStage(
                LandscapeDataLoads.getPrestageData(),
                this.heightCeilingAndFloor,
                LandscapeUtils.NUMBER_OF_SPITE_PER_PRESTAGE_WIDTH);
    }

    /**
     * @inheritDoc
     */
    @Override
    public MapStageImpl stage1() {
        return mapStageGenerator.convertDataToMapStage(
                LandscapeDataLoads.getStage1Data(),
                this.heightCeilingAndFloor,
                LandscapeUtils.NUMBER_OF_SPITE_PER_STAGE_WIDTH);
    }

    /**
     * @inheritDoc
     */
    @Override
    public MapStageImpl stage2() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stage2'");
    }

    /**
     * @inheritDoc
     */
    @Override
    public MapStageImpl stage3() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stage3'");
    }

    /**
     * @inheritDoc
     */
    @Override
    public MapStageImpl stage4() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stage4'");
    }

    /**
     * @inheritDoc
     */
    @Override
    public MapStageImpl stage5() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stage5'");
    }

    /**
     * @inheritDoc
     */
    @Override
    public MapStageImpl stage6() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stage6'");
    }
}
