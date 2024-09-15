package scramble.controller.map;

import java.util.List;
import java.util.ArrayList;

import scramble.model.common.api.Pair;
import scramble.model.common.impl.PairImpl;
import scramble.model.map.api.MapColumn;
import scramble.model.map.api.MapStageFactory;
import scramble.model.map.impl.MapStageFactoryImpl;
import scramble.model.map.util.LandUtils;
import scramble.model.map.util.enums.LandBehaviour;
import scramble.view.compact.GameView;
import scramble.view.compact.LandscapePanel;

/**
 * The {@code MapController} class is responsible for the preparation of data
 * related to {@link List} model
 * for presentation in the view layer.
 *
 * <p>
 * This controller collects and organizes data from the {@link MapStageFactory},
 * making sure it is ready for display.
 * </p>
 *
 * @see MapStageFactory
 */
public class MapController {
    private static final MapStageFactory<MapColumn> STAGE_FACTORY = new MapStageFactoryImpl();
    private static List<Integer> stageStartingX = new ArrayList<>();
    private static List<Pair<Integer, Integer>> flatPositions = new ArrayList<>();
    private static List<Pair<Integer, Integer>> brickWallPosition = new ArrayList<>();
    private static int endOfMapX;

    private final List<MapColumn> columns;
    private int columnIndex;
    private int currentX;

    /**
     * Controller for the class {@link MapController}.
     */
    public MapController() {
        final List<List<MapColumn>> stages = this.fillStage();
        this.columnIndex = 0;
        this.columns = new ArrayList<>();
        this.fillColumns(stages);
        this.currentX = 0;
    }

    /**
     * Return the columns that have to be displayed. For optimization purpose, only
     * a fiew columns will be returned.
     * <p>
     * The number of returned columns is defined by
     * {@link LandscapePanel#TOTAL_COLUMNS_LOADED}.
     * </p>
     *
     * @return a list of columns
     */
    public List<MapColumn> getColumnsToDisplay() {
        final List<MapColumn> columnsToDisplay = new ArrayList<>();
        final int end;
        if (columnIndex + LandscapePanel.TOTAL_COLUMNS_LOADED >= columns.size()) {
            end = columnIndex + LandscapePanel.TOTAL_COLUMNS_LOADED - columns.size();
        } else {
            end = LandscapePanel.TOTAL_COLUMNS_LOADED;
        }
        for (int i = 0; i < end; i++) {
            columnsToDisplay.add(columns.get(this.columnIndex + i));
        }
        this.currentX = columns.get(columnIndex).getX();
        this.columnIndex += LandscapePanel.EXTRA_COLUMNS_LOADED;
        if (this.columnIndex + (LandUtils.dividePixelPerSprite(GameView.WINDOW_WIDTH))
                + 4 > this.columns
                        .size()) {
            this.columnIndex = 0;
        }
        return columnsToDisplay;
    }

    /**
     * Reset the Landscape to the indicated x position.
     *
     * @param x position to reset the Landscape
     */
    public void resetToX(final int x) {
        this.columnIndex = x / LandUtils.PIXEL_PER_LAND_SPRITE_SIDE;
    }

    /**
     * Getter for the list of the starting x position of
     * each stage.
     *
     * @return the list with all the starter x position of each stage
     */
    public static List<Integer> getStageStartingX() {
        return new ArrayList<>(stageStartingX);
    }

    /**
     * Getter for the flat floor Position of the stages.
     * 
     * @return the flat floor position
     */
    public List<Pair<Integer, Integer>> getFlatFloorPositions() {
        return new ArrayList<>(flatPositions);
    }

    /**
     * Getter for the flat floor Position of the stages.
     * 
     * @return the flat floor position
     */
    public List<Pair<Integer, Integer>> getBrickFloorPosition() {
        return new ArrayList<>(brickWallPosition);
    }

    /**
     * Getter for current X position.
     * 
     * @return the current x position
     */
    public int getCurrentMapX() {
        return currentX;
    }

    /**
     * Getter for the x coordinate of the end of the map.
     * 
     * @return x coordinate of the end of the map
     */
    public static int getEndOfMapX() {
        return endOfMapX - GameView.WINDOW_WIDTH / 2;
    }

    /**
     * Return the number of columns in this controller.
     *
     * @return the number of columns in this controller
     */
    public int getMapSize() {
        return this.columns.size();
    }

    private List<List<MapColumn>> fillStage() {
        final List<List<MapColumn>> stages = new ArrayList<>();
        stages.add(STAGE_FACTORY.prestage());
        stages.add(STAGE_FACTORY.stage1());
        stages.add(STAGE_FACTORY.stage2());
        stages.add(STAGE_FACTORY.stage3());
        stages.add(STAGE_FACTORY.stage4());
        stages.add(STAGE_FACTORY.stage5());
        stages.add(STAGE_FACTORY.stage6());
        return stages;
    }

    private void fillColumns(final List<List<MapColumn>> stages) {
        int x = 0;
        for (final List<MapColumn> mapStage : stages) {
            stageStartingX.add(x * LandUtils.PIXEL_PER_LAND_SPRITE_SIDE);
            for (final MapColumn column : mapStage) {
                column.updateX(x * column.gettWidth());
                this.columns.add(column);
                if (column.getFloorBehaviour() == LandBehaviour.FLAT && stageStartingX.size() > 1) {
                    flatPositions.add(new PairImpl<>(x * LandUtils.PIXEL_PER_LAND_SPRITE_SIDE,
                            column.getFloorPosition().getSecondElement()));
                }
                if (column.getFloorBehaviour() == LandBehaviour.BRICK) {
                    brickWallPosition.add(new PairImpl<>(x * LandUtils.PIXEL_PER_LAND_SPRITE_SIDE,
                            column.getFloorPosition().getSecondElement()));
                }
                x++;
            }
        }
        endOfMapX = x * LandUtils.PIXEL_PER_LAND_SPRITE_SIDE - GameView.WINDOW_WIDTH / 2;
    }

}
