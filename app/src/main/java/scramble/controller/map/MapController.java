package scramble.controller.map;

import java.util.List;

import java.util.ArrayList;
import java.awt.image.BufferedImage;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.MapStageFactoryImpl;
import scramble.model.map.api.MapStageFactory;
import scramble.model.map.api.MapStage;
import scramble.model.map.impl.MapElement;
import scramble.model.map.utils.LandscapeUtils;
import scramble.model.map.utils.enums.LandscapePart;
import scramble.view.compact.GameView;
import scramble.view.compact.LandscapePanel;

/**
 * The {@code MapController} class is responsible for the preparation of data
 * related to {@link MapStage} model
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
    private static final MapStageFactory STAGE_FACTORY = new MapStageFactoryImpl();
    private final List<List<MapElement>> ceiling;
    private final List<List<MapElement>> floor;
    private static List<Integer> stageStartingX = new ArrayList<>();

    private int columnIndex;

    /**
     * Controller for the class {@link MapController}.
     */
    public MapController() {
        final List<MapStage> stages = this.fillMapStage();
        this.columnIndex = 0;
        this.ceiling = new ArrayList<>();
        this.floor = new ArrayList<>();
        this.fillColumns(stages);
        this.fillCeiling();
        this.fillFloor();
    }

    private List<MapStage> fillMapStage() {
        final List<MapStage> stages = new ArrayList<>();
        stages.add(STAGE_FACTORY.prestage());
        stages.add(STAGE_FACTORY.stage1());
        return stages;
    }

    private void fillColumns(final List<MapStage> stages) {
        int x = 0;
        for (final MapStage mapStage : stages) {
            stageStartingX.add(x);
            for (int i = 0; i < mapStage.size(); i++) {
                this.ceiling.add(new ArrayList<>());
                this.floor.add(new ArrayList<>());
                final MapElement ceilingColumn = mapStage.getCloumnCeiling(i);
                final MapElement floorColumn = mapStage.getCloumnFloor(i);
                ceilingColumn.updatePosition(
                        new PairImpl<Integer, Integer>(x * ceilingColumn.getWidth(), ceilingColumn.getY()));
                floorColumn.updatePosition(
                        new PairImpl<Integer, Integer>(x * floorColumn.getWidth(), floorColumn.getY()));
                this.ceiling.get(x).add(ceilingColumn);
                this.floor.get(x).add(floorColumn);
                x++;
            }
        }
    }

    private void fillCeiling() {
        final LandscapeUtils mapUtils = new LandscapeUtils();
        final BufferedImage green = mapUtils.getSprite(LandscapePart.GREEN_SQUARE);
        for (int i = 0; i < this.ceiling.size(); i++) {
            for (int y = 0; y < this.ceiling.get(i).get(0).getY(); y += LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE) {
                this.ceiling.get(i).add(new MapElement(
                        this.ceiling.get(i).get(0).getX(), y,
                        LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE,
                        LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE,
                        green));
            }
        }
    }

    private void fillFloor() {
        final LandscapeUtils mapUtils = new LandscapeUtils();
        final BufferedImage green = mapUtils.getSprite(LandscapePart.GREEN_SQUARE);
        for (int i = 0; i < this.floor.size(); i++) {
            for (int y = this.floor.get(i).get(0).getY()
                    + LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE; y < LandscapeUtils.NUMBER_OF_SPITE_PER_STAGE_HEIGHT
                            * LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE; y += LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE) {
                this.floor.get(i).add(new MapElement(this.floor.get(i).get(0).getX(), y,
                        this.floor.get(i).get(0).getWidth(),
                        this.floor.get(i).get(0).getHeight(),
                        green));
            }
        }
    }

    /**
     * Return the number of columns in this controller.
     * 
     * @return the number of columns in this controller
     */
    public int getMapSize() {
        return this.ceiling.size();
    }

    // to-do: creazione classe Column
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
    public List<List<MapElement>> getColumnsToDisplay() {
        final List<List<MapElement>> columns = new ArrayList<>();
        for (int i = 0; i < LandscapePanel.TOTAL_COLUMNS_LOADED; i++) {
            final List<MapElement> column = new ArrayList<>();
            column.addAll(this.ceiling.get(this.columnIndex + i));
            column.addAll(this.floor.get(this.columnIndex + i));
            columns.add(column);
        }
        this.columnIndex += LandscapePanel.EXTRA_COLUMNS_LOADED;
        if (this.columnIndex + (GameView.WINDOW_WIDTH / LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE) + 4 > this.floor
                .size()) {
            this.columnIndex = 0;
        }
        // System.out.println(columns.size());
        return columns;
    }

    /**
     * Reset the Landscape to the indicated x position.
     * 
     * @param x position to reset the Landscape
     */
    public void resetToX(final int x) {
        this.columnIndex = x;
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

}
