package scramble.model.map.api;

import java.awt.image.BufferedImage;

public interface MapElement {

    /**
     * Getter che restituisce l'altezza del MapElement
     * @return int this.height
     */
    int getHeight();

    /**
     * Getter che restituisce il BufferedImage che rappresenta la sprite del MapElement
     * @return BufferedImage this.sprite
     */
    BufferedImage getSprite();
} 
