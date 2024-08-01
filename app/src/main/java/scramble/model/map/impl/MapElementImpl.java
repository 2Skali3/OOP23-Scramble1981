package scramble.model.map.impl;

import java.awt.image.BufferedImage;

import scramble.model.map.api.MapElement;

public class MapElementImpl implements MapElement{

    private int height;
    private BufferedImage sprite;

    public MapElementImpl(int height, BufferedImage sprite){
        this.height = height;
        this.sprite = sprite;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public BufferedImage getSprite() {
        return this.sprite;
    }
    
}
