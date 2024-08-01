package scramble.model.map.utils;

import java.awt.image.BufferedImage;

import java.util.ArrayList;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.impl.MapElementImpl;

public final class Converter {

    /** 
     * Funzione che serve per la conversione tra una colonna di MapStageImpl e il relativo ArrayList di BufferedImage che essa rappresenta
     * @param column; coppia di MapElement che vogliamo tradurre in una colonna di BufferedImage
     * @return returnColumn; ArrayList che rappresenta la colonna di BufferedImage pronta ad essere visualizzata a schermo
     */
    public static final ArrayList<BufferedImage> convertMapStageImplToBufferedImage(PairImpl<MapElementImpl, MapElementImpl> column){

        ArrayList<BufferedImage> returnColumn = new ArrayList<>();

        LandscapeUtils mapUtils = new LandscapeUtils();

        BufferedImage green = mapUtils.getSprite(LandscapePart.LandscapeSprite.GREEN_SQUARE);
        BufferedImage sky = mapUtils.getSprite(LandscapePart.LandscapeSprite.SKY);

        int ceilingHeight = column.getFirstElement().getHeight();
        int floorHeight = column.getSecondElement().getHeight();

        boolean isGreen = (ceilingHeight != -1);
        
        for(int y = 0; y < LandscapeUtils.NUMBER_OF_SPITE_PER_STAGE_HEIGHT; y++){
            if(y == ceilingHeight){
                isGreen = false;
                returnColumn.add(column.getFirstElement().getSprite());
            }
            else if(y == floorHeight){
                isGreen = true;
                returnColumn.add(column.getSecondElement().getSprite());
            }
            else if(isGreen)
                returnColumn.add(green);
            else 
                returnColumn.add(sky);
        }

        return returnColumn;
    }

}
