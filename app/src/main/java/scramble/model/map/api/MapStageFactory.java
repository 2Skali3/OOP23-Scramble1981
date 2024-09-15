package scramble.model.map.api;

import java.util.List;

/**
 * The {@code MapStageFactory} interface is a factory for the generation of the
 * landscape in the stages.
 * 
 * <p>
 * This interface define the stages rappresentation as
 * {@link List}.
 * </p>
 * 
 * @param <X> the type of element designed to rappresent the data contained into
 *            the {@link List}
 */
public interface MapStageFactory<X> {
    /**
     * Prestage of the game.
     * 
     * @return the MapStage that contain the prestage
     */
    List<X> prestage();

    /**
     * Stage 1 of the game.
     * 
     * @return the MapStage that contain the stage 1
     */
    List<X> stage1();

    /**
     * Stage 2 of the game.
     * 
     * @return the MapStage that contain the stage 2
     */
    List<X> stage2();

    /**
     * Stage 3 of the game.
     * 
     * @return the MapStage that contain the stage 3
     */
    List<X> stage3();

    /**
     * Stage 4 of the game.
     * 
     * @return the MapStage that contain the stage 4
     */
    List<X> stage4();

    /**
     * Stage 5 of the game.
     * 
     * @return the MapStage that contain the stage 5
     */
    List<X> stage5();

    /**
     * Stage 6 of the game.
     * 
     * @return the MapStage that contain the stage 6
     */
    List<X> stage6();
}
