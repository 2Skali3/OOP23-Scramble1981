package scramble.model.map.impl;

import java.util.ArrayList;
import java.util.List;

import scramble.model.common.api.Pair;
import scramble.model.common.impl.PairImpl;
import scramble.model.map.api.MapStage;
import scramble.model.map.util.enums.TerrainType;

/**
 * Implementation for the interface MapStage. This implementation use an List
 * for the storage of the columns.
 * 
 * @see MapStage
 */
public class MapStageImpl implements MapStage {

    private final List<MapElement> ceiling;
    private final List<MapElement> floor;
    private final TerrainType terrainType;

    /**
     * Constructor for MapStageImpl.
     * 
     * @param terrainType the terrain type of the stage
     */
    public MapStageImpl(final TerrainType terrainType) {
        this.terrainType = terrainType;
        this.ceiling = new ArrayList<>();
        this.floor = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.ceiling.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addColumn(final Pair<MapElement, MapElement> column) {
        this.ceiling.add(column.getFirstElement());
        this.floor.add(column.getSecondElement());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<MapElement, MapElement> getColumn(final int index) {
        return new PairImpl<MapElement, MapElement>(this.ceiling.get(index), this.floor.get(index));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MapElement getCloumnCeiling(final int index) {
        return this.ceiling.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MapElement getCloumnFloor(final int index) {
        return floor.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MapElement> getCeiling() {
        return new ArrayList<>(this.ceiling);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MapElement> getFloor() {
        return new ArrayList<>(this.floor);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setCeiling(final List<MapElement> ceiling) {
        this.ceiling.addAll(ceiling);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setFloor(final List<MapElement> floor) {
        this.floor.addAll(floor);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public TerrainType getTerrainType() {
        return this.terrainType;
    }
}
