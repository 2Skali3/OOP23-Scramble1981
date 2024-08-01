package scramble.model.map;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.impl.MapStageImpl;
import scramble.model.map.utils.LandscapeDataLoads;
import scramble.model.map.utils.LandscapeUtils;

public class MapStageFactoryImpl {

    public static final int starterCeilingHeight = -1;
    public static final int starterFloorHeight = 35;

    private MapStageGenerator mapStageGenerator;
    private PairImpl<Integer,Integer> heightCeilingAndFloor;

    public MapStageFactoryImpl(){
        this.heightCeilingAndFloor = new PairImpl<Integer,Integer>(MapStageFactoryImpl.starterCeilingHeight, MapStageFactoryImpl.starterFloorHeight);
        this.mapStageGenerator = new MapStageGenerator();
    }

    /**
     * 
     * @return
     */
    public MapStageImpl prestage(){
        return mapStageGenerator.convertDataToMapStage(
            LandscapeDataLoads.getPrestageData(), 
            new PairImpl<>(-1, 35), 
            LandscapeUtils.NUMBER_OF_SPITE_PER_PRESTAGE_WIDTH
        );
    }

    public MapStageImpl stage1(){
        return mapStageGenerator.convertDataToMapStage(LandscapeDataLoads.getStage1Data(), new PairImpl<>(-1, 35), LandscapeUtils.NUMBER_OF_SPITE_PER_STAGE_WIDTH);
    }

}
