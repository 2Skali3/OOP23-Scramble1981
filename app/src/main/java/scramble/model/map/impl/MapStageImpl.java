package scramble.model.map.impl;

import java.util.ArrayList;
import java.util.List;

import scramble.model.map.api.MapStage;

/**
 * Implementation for the interface MapStage. This implementation use an List
 * for the storage of the columns.
 * 
 * @param <X> the type of element designed to rappresent the data contained into
 *            the {@link MapStage}
 * 
 * @see MapStage
 */
public class MapStageImpl<X> implements MapStage<X> {

    private final List<X> columns;

    /**
     * Constructor for MapStageImpl.
     */
    public MapStageImpl() {
        this.columns = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.columns.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public X getColumn(final int index) {
        return this.columns.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addColumn(final X column) {
        this.columns.add(column);
    }
}
