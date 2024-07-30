package scramble.model.common.impl;

import java.awt.Polygon;

/**
 * This class handles the game element's hit boxes.
 */
public class HitBox extends Polygon implements Cloneable {

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
