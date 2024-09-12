package scramble.model.map.api;

/**
 * The {@code MapStageFactory} interface is a factory for the generation of the
 * landscape in the stages.
 * 
 * <p>
 * This interface define the stages rappresentation as
 * {@link MapStage}.
 * </p>
 * 
 * @param <X> the type of element designed to rappresent the data contained into
 *            the {@link MapStage}
 */
public interface MapStageFactory<X> {
    /**
     * Prestage of the game.
     * 
     * @return the MapStage that contain the prestage
     */
    MapStage<X> prestage();

    /**
     * Stage 1 of the game.
     * 
     * @return the MapStage that contain the stage 1
     */
    MapStage<X> stage1();

    /**
     * Stage 2 of the game.
     * 
     * @return the MapStage that contain the stage 2
     */
    MapStage<X> stage2();

    /**
     * Stage 3 of the game.
     * 
     * @return the MapStage that contain the stage 3
     */
    MapStage<X> stage3();

    /**
     * Stage 4 of the game.
     * 
     * @return the MapStage that contain the stage 4
     */
    MapStage<X> stage4();

    /**
     * Stage 5 of the game.
     * 
     * @return the MapStage that contain the stage 5
     */
    MapStage<X> stage5();

    /**
     * Stage 6 of the game.
     * 
     * @return the MapStage that contain the stage 6
     */
    MapStage<X> stage6();
}
