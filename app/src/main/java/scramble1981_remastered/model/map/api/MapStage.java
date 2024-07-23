package scramble1981_remastered.model.map.api;

import java.awt.image.BufferedImage;
import java.util.List;

public interface MapStage {
    /*
     * Get the list of the map component of that stage, every part of that list it's like a column that start at the top of the screen and end at the end of it
     */
    List<BufferedImage> getStageLandScapeColumn(int index);

    void setStageLandScape(int index, List<BufferedImage> stageLandScapeColumn); 
}
