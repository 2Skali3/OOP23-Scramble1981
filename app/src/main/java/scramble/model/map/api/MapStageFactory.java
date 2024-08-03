package scramble.model.map.api;

import scramble.model.map.impl.MapStageImpl;

/**
 * Factory for the generation of the stage.
 */
public interface MapStageFactory {
    /**
     * Prestage of the game.
     * 
     * @return the MapStageImpl that contain the prestage
     */
    MapStageImpl prestage();

    /**
     * Stage 1 of the game.
     * 
     * @return the MapStageImpl that contain the stage 1
     */
    MapStageImpl stage1();

    /**
     * Stage 2 of the game.
     * 
     * @return the MapStageImpl that contain the stage 2
     */
    MapStageImpl stage2();

    /**
     * Stage 3 of the game.
     * 
     * @return the MapStageImpl that contain the stage 3
     */
    MapStageImpl stage3();

    /**
     * Stage 4 of the game.
     * 
     * @return the MapStageImpl that contain the stage 4
     */
    MapStageImpl stage4();

    /**
     * Stage 5 of the game.
     * 
     * @return the MapStageImpl that contain the stage 5
     */
    MapStageImpl stage5();

    /**
     * Stage 6 of the game.
     * 
     * @return the MapStageImpl that contain the stage 6
     */
    MapStageImpl stage6();
}
