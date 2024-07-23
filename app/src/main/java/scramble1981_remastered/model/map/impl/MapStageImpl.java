package scramble1981_remastered.model.map.impl;

import java.awt.image.BufferedImage;
import java.util.List;

import scramble1981_remastered.model.map.api.MapStage;

import java.util.ArrayList;

public class MapStageImpl implements MapStage
{
    private List<List<BufferedImage>> stageLandScape;
    
    public MapStageImpl(){
        this.stageLandScape = new ArrayList<>();        
    }

    public int size()
    {
        return this.stageLandScape.size();
    }

    public boolean hasColumn(int index)
    {
        return index < this.stageLandScape.size();
    }

    @Override
    public void setStageLandScape(int index, List<BufferedImage> stageLandScapeColumn) 
    {
        this.stageLandScape.add(index, stageLandScapeColumn);
    }

    @Override
    public List<BufferedImage> getStageLandScapeColumn(int index) {
        return this.stageLandScape.get(index);
    }
    
}

