package scramble1981_remastered.model.map.impl;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import scramble1981_remastered.model.map.LandScapePart;
import scramble1981_remastered.model.map.LandScapePart.LandScapeSprite;
import scramble1981_remastered.model.map.MapUtils;
import scramble1981_remastered.model.map.api.MapStageFactory;

public class MapStageFactoryImpl implements MapStageFactory
{
    private MapUtils mapUtils;
    private enum LandScapeInternalState { UP, DOWN, FLOOR }

    public MapStageFactoryImpl()
    {
        this.mapUtils = new MapUtils();
    }

    private int fromPercentageToNumber(int percentage, int fullPercentage, int fullNumber)
    {
        return fullNumber - (fullNumber * percentage / fullPercentage);
    }

    private LandScapeSprite getSprite(LandScapeInternalState behavior)
    {
        final int[] thresholds  = {85, 100};
        final LandScapeSprite[] floorSprite = {LandScapeSprite.BOTTOM_FLAT_FLOOR, LandScapeSprite.TRIANGLE_CLIFF};
        final LandScapeSprite[] downSprite = {LandScapeSprite.ROUND_CLIMB, LandScapeSprite.BOOT_CLIMB, LandScapeSprite.CROWN_CLIMB, LandScapeSprite.STANDARD_CLIMB};
        final LandScapeSprite[] upSprite = {LandScapeSprite.MIRROR_ROUND_CLIMB, LandScapeSprite.MIRROR_BOOT_CLIMB, LandScapeSprite.MIRROR_CROWN_CLIMB, LandScapeSprite.MIRROR_STANDARD_CLIMB};
        Random rand = new Random();
        int selectedItem;

        if(behavior == LandScapeInternalState.UP)
            return upSprite[rand.nextInt(upSprite.length)];

        else if (behavior == LandScapeInternalState.DOWN)
            return downSprite[rand.nextInt(downSprite.length)];

        selectedItem = rand.nextInt(thresholds[thresholds.length - 1]);
        
        for(int i = 0; i < thresholds.length - 1; i++)
            if(selectedItem < thresholds[i])
                return floorSprite[i];
        
        return floorSprite[thresholds.length - 1];
    }

     /**
     *  Funzione per la generazione del terreno da Liste:
     *  @param percentageBase  -> valore percentuale usato come base per le proporzioni
     *  @param startHeight -> valore di partenza dell'altezza
     *  @param behavior  -> lista che contiene che comportamento deve tenere il terreno. La lista inizia sempre con il comportamento iniziale  
     *  @param targetHeight -> lista dei valori target dell'altezza del terreno che, una volta raggiunti, cambieranno il comportamento del terreno
     *  @param targetWidth  -> lista dei valori target della lunghezza del terreno che, una volta raggiunti, cambieranno il comportamento del terreno
     *  @param maxWidth -> specifica la lunghezza sull'asse delle x (di norma è MapUtils.STAGE_WIDTH tranne per la zona iniziale)
     *  @return -> stage con le caratteristiche descritte dagli ArrayList in input 
     */
    private MapStageImpl floorOnly(int percentageBase, int startHeight, ArrayList<LandScapeInternalState> behavior, ArrayList<Integer> targetHeight, ArrayList<Integer> targetWidth, int maxWidth)
    {
        int height = MapUtils.STAGE_HEIGHT - ((MapUtils.STAGE_HEIGHT * startHeight) / percentageBase);
        int internalStateIndex = 0;
        int tempX = 0;
        int indexTargetX = 0;
        int indexTargetY = 0;

        MapStageImpl returnStage = new MapStageImpl();
        List<BufferedImage> tempColumn = new ArrayList<>();
        
        for(int x = 0; x < maxWidth; x++)
        {
            for (int y = 0; y < MapUtils.STAGE_HEIGHT; y++)
            {
                if(y < height)
                    tempColumn.add(mapUtils.getSprite(LandScapePart.LandScapeSprite.SKY));
                else if (y == height) 
                    tempColumn.add(mapUtils.getSprite(this.getSprite(behavior.get(internalStateIndex))));
                else
                    tempColumn.add(mapUtils.getSprite(LandScapePart.LandScapeSprite.GREEN_SQUARE));
            }
            returnStage.setStageLandScape(x,tempColumn);
            tempColumn = new ArrayList<>();
            
            switch (behavior.get(internalStateIndex)) {
                case FLOOR:
                    if(x - tempX == targetWidth.get(indexTargetX))
                    {
                        internalStateIndex++;
                        indexTargetX++;
                    }
                    break;
                case UP:
                    if(height == targetHeight.get(indexTargetY))
                    {
                        internalStateIndex++;
                        indexTargetY++;
                        tempX = x;
                    }
                    else
                        height--;
                    break;
                default:
                    if(height == targetHeight.get(indexTargetY))
                    {
                        internalStateIndex++;
                        indexTargetY++;
                        tempX = x;
                    }
                    else
                        height++;
            }
        }        
        return returnStage;

    }
   
    @Override
    public MapStageImpl starterFlatLand() {
        final ArrayList<LandScapeInternalState> behavior = new ArrayList<>(Arrays.asList(LandScapeInternalState.FLOOR));
        final ArrayList<Integer> targetHeight = new ArrayList<>(); 
        final ArrayList<Integer> targetWidth = new ArrayList<>(Arrays.asList(MapUtils.STAGE_WIDTH));
        final int percentageBase = 100;
        final int height = 26;
        final int maxWidth = 35;

        return this.floorOnly(percentageBase, height, behavior, targetHeight, targetWidth, maxWidth);
    }

    @Override
    public MapStageImpl stage1() {
        // start at 26%, up to 66%, down to 50%, up to 66%, down to 26%
        final int breakPoint = 20;
        final int percentageBase = 100;
        final int summit = 66;
        final int midZone = 55;
        final int bottom = 26;

        final ArrayList<LandScapeInternalState> behavior = new ArrayList<>(Arrays.asList(LandScapeInternalState.FLOOR, LandScapeInternalState.UP, LandScapeInternalState.DOWN, LandScapeInternalState.FLOOR, LandScapeInternalState.UP, LandScapeInternalState.DOWN, LandScapeInternalState.FLOOR));
        final ArrayList<Integer> targetHeight = new ArrayList<>(Arrays.asList(fromPercentageToNumber(summit, percentageBase, MapUtils.STAGE_HEIGHT), fromPercentageToNumber(midZone, percentageBase, MapUtils.STAGE_HEIGHT), fromPercentageToNumber(summit, percentageBase, MapUtils.STAGE_HEIGHT), fromPercentageToNumber(bottom, percentageBase, MapUtils.STAGE_HEIGHT))); 
        final ArrayList<Integer> targetWidth = new ArrayList<>(Arrays.asList(breakPoint, breakPoint, MapUtils.STAGE_WIDTH));
        final int height = 26;
        final int maxWidth = MapUtils.STAGE_WIDTH;

        return this.floorOnly(percentageBase, height, behavior, targetHeight, targetWidth, maxWidth);
    }

    @Override
    public MapStageImpl stage2() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stage2'");
    }

    @Override
    public MapStageImpl stage3() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stage3'");
    }

    @Override
    public MapStageImpl stage4() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stage4'");
    }

    @Override
    public MapStageImpl stage5() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stage5'");
    }

    @Override
    public MapStageImpl stage6() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stage6'");
    }
    
}
