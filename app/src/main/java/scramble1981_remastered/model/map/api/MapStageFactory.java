package scramble1981_remastered.model.map.api;

import scramble1981_remastered.model.map.impl.MapStageImpl;

public interface MapStageFactory 
{
    MapStageImpl starterFlatLand();
    
    MapStageImpl stage1();
    MapStageImpl stage2();
    MapStageImpl stage3();
    MapStageImpl stage4();
    MapStageImpl stage5();
    MapStageImpl stage6();
} 
