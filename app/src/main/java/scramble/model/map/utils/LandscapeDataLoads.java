package scramble.model.map.utils;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.utils.impl.CSVReaderImpl;
import scramble.model.map.utils.impl.CSVReaderImpl.Behaviour;

import java.util.ArrayList;

/**
 * Classe che contiene metodi statici che gestiscono la lettura dei file csv contenenti i dati
 */
public class LandscapeDataLoads {

    public static final String PRESTAGE_CEILING =           "stage/prestage_ceiling.csv"; 
    public static final String PRESTAGE_FLOOR =             "stage/prestage_floor.csv"; 
    public static final String STAGE_1_CEILING_FILE_PATH =  "stage/stage1_ceiling.csv"; 
    public static final String STAGE_1_FLOOR_FILE_PATH =    "stage/stage1_floor.csv"; 
    public static final String STAGE_2_CEILING_FILE_PATH =  "stage/stage2_ceiling.csv"; 
    public static final String STAGE_2_FLOOR_FILE_PATH =    "stage/stage2_floor.csv"; 
    public static final String STAGE_3_CEILING_FILE_PATH =  "stage/stage3_ceiling.csv"; 
    public static final String STAGE_3_FLOOR_FILE_PATH =    "stage/stage3_floor.csv"; 
    public static final String STAGE_4_CEILING_FILE_PATH =  "stage/stage4_ceiling.csv"; 
    public static final String STAGE_4_FLOOR_FILE_PATH =    "stage/stage4_floor.csv"; 
    public static final String STAGE_5_CEILING_FILE_PATH =  "stage/stage5_ceiling.csv"; 
    public static final String STAGE_5_FLOOR_FILE_PATH =    "stage/stage5_floor.csv"; 
    public static final String STAGE_6_CEILING_FILE_PATH =  "stage/stage6_ceiling.csv"; 
    public static final String STAGE_6_FLOOR_FILE_PATH =    "stage/stage6_floor.csv"; 
    
    private static final CSVReaderImpl csvReader = new CSVReaderImpl();

    /**
     * Metodo per la lettura ed elaborazione dei file relativi al prestage
     * @return rawData presi dal csv pronti ad essere elaborati
     */
    public static  PairImpl<ArrayList<PairImpl<Integer,Behaviour>>, ArrayList<PairImpl<Integer,Behaviour>>> getPrestageData(){
        return new PairImpl<ArrayList<PairImpl<Integer,Behaviour>>, ArrayList<PairImpl<Integer,Behaviour>>>(
            csvReader.readCSV(PRESTAGE_CEILING),
            csvReader.readCSV(PRESTAGE_FLOOR)
        ); 
    }

    /**
     * Metodo per la lettura ed elaborazione dei file relativi allo stage1
     * @return rawData presi dal csv pronti ad essere elaborati
     */
    public static  PairImpl<ArrayList<PairImpl<Integer,Behaviour>>, ArrayList<PairImpl<Integer,Behaviour>>> getStage1Data(){
        return new PairImpl<ArrayList<PairImpl<Integer,Behaviour>>, ArrayList<PairImpl<Integer,Behaviour>>>(
            csvReader.readCSV(STAGE_1_CEILING_FILE_PATH),
            csvReader.readCSV(STAGE_1_FLOOR_FILE_PATH)
        ); 
    }

    /**
     * Metodo per la lettura ed elaborazione dei file relativi allo stage2
     * @return rawData presi dal csv pronti ad essere elaborati
     */
    public static  PairImpl<ArrayList<PairImpl<Integer,Behaviour>>, ArrayList<PairImpl<Integer,Behaviour>>> getStage2Data(){
        return new PairImpl<ArrayList<PairImpl<Integer,Behaviour>>, ArrayList<PairImpl<Integer,Behaviour>>>(
            csvReader.readCSV(STAGE_2_CEILING_FILE_PATH),
            csvReader.readCSV(STAGE_2_FLOOR_FILE_PATH)
        ); 
    }

    /**
     * Metodo per la lettura ed elaborazione dei file relativi allo stage3
     * @return rawData presi dal csv pronti ad essere elaborati
     */
    public static  PairImpl<ArrayList<PairImpl<Integer,Behaviour>>, ArrayList<PairImpl<Integer,Behaviour>>> getStage3Data(){
        return new PairImpl<ArrayList<PairImpl<Integer,Behaviour>>, ArrayList<PairImpl<Integer,Behaviour>>>(
            csvReader.readCSV(STAGE_3_CEILING_FILE_PATH),
            csvReader.readCSV(STAGE_3_FLOOR_FILE_PATH)
        ); 
    }

    /**
     * Metodo per la lettura ed elaborazione dei file relativi allo stage4
     * @return rawData presi dal csv pronti ad essere elaborati
     */
    public static  PairImpl<ArrayList<PairImpl<Integer,Behaviour>>, ArrayList<PairImpl<Integer,Behaviour>>> getStage4Data(){
        return new PairImpl<ArrayList<PairImpl<Integer,Behaviour>>, ArrayList<PairImpl<Integer,Behaviour>>>(
            csvReader.readCSV(STAGE_4_CEILING_FILE_PATH),
            csvReader.readCSV(STAGE_4_FLOOR_FILE_PATH)
        ); 
    }

    /**
     * Metodo per la lettura ed elaborazione dei file relativi allo stage5
     * @return rawData presi dal csv pronti ad essere elaborati
     */
    public static  PairImpl<ArrayList<PairImpl<Integer,Behaviour>>, ArrayList<PairImpl<Integer,Behaviour>>> getStage5Data(){
        return new PairImpl<ArrayList<PairImpl<Integer,Behaviour>>, ArrayList<PairImpl<Integer,Behaviour>>>(
            csvReader.readCSV(STAGE_5_CEILING_FILE_PATH),
            csvReader.readCSV(STAGE_5_FLOOR_FILE_PATH)
        ); 
    }

    /**
     * Metodo per la lettura ed elaborazione dei file relativi allo stage6
     * @return rawData presi dal csv pronti ad essere elaborati
     */
    public static  PairImpl<ArrayList<PairImpl<Integer,Behaviour>>, ArrayList<PairImpl<Integer,Behaviour>>> getStage6Data(){
        return new PairImpl<ArrayList<PairImpl<Integer,Behaviour>>, ArrayList<PairImpl<Integer,Behaviour>>>(
            csvReader.readCSV(STAGE_6_CEILING_FILE_PATH),
            csvReader.readCSV(STAGE_6_FLOOR_FILE_PATH)
        ); 
    }
}
