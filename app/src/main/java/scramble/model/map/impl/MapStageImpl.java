package scramble.model.map.impl;

import java.util.ArrayList;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.api.MapStage;

/**
 * Implementation for the interface MapStage.
 * This implementation use an ArrayList for the storage of the columns.
 * @see MapStage
 */
public class MapStageImpl implements MapStage {
    private ArrayList<PairImpl<MapElementImpl, MapElementImpl>> columns;
    /**
     * Constructor for MapStageImpl.
     */
    public MapStageImpl() {
        this.columns = new ArrayList<>();
    }
    /**
     * @inheritDoc
     */
    @Override
    public int size() {
        return this.columns.size();
    }
    /**
     * @inheritDoc
     */
    @Override
    public void addColumn(final PairImpl<MapElementImpl, MapElementImpl> column) {
        this.columns.add(column);
    }
    /**
     * @inheritDoc
     */
    @Override
    public PairImpl<MapElementImpl, MapElementImpl> getColumn(final int index) {
        return this.columns.get(index);
    }
}