package scramble.model.map.impl;

import java.util.ArrayList;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.api.MapStage;

public class MapStageImpl implements MapStage{

    private ArrayList<PairImpl<MapElementImpl, MapElementImpl>> columns;
    
    public MapStageImpl(){
        this.columns = new ArrayList<>();
    }

    @Override
    public int size(){
        return this.columns.size();
    }

    @Override
    public void addColumn(PairImpl<MapElementImpl, MapElementImpl> column) {
        this.columns.add(column);
    }

    @Override
    public PairImpl<MapElementImpl, MapElementImpl> getColumn(int index) {
        return this.columns.get(index);
    }
    
}
