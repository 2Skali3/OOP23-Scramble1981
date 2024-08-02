package scramble.model.map.utils;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.utils.impl.CSVReaderImpl;
import scramble.model.map.utils.impl.CSVReaderImpl.Behaviour;

import java.util.ArrayList;

/**
 * Class that contains static methods for the management of the csv files
 * reading for data scraping.
 */
public final class LandscapeDataLoads {
    /**
     * Prestage ceiling files relative path.
     */
    private static final String PRESTAGE_CEILING = "stage/prestage_ceiling.csv";
    private static final String PRESTAGE_FLOOR = "stage/prestage_floor.csv";
    private static final String STAGE_1_CEILING_FILE_PATH = "stage/stage1_ceiling.csv";
    private static final String STAGE_1_FLOOR_FILE_PATH = "stage/stage1_floor.csv";
    private static final String STAGE_2_CEILING_FILE_PATH = "stage/stage2_ceiling.csv";
    private static final String STAGE_2_FLOOR_FILE_PATH = "stage/stage2_floor.csv";
    private static final String STAGE_3_CEILING_FILE_PATH = "stage/stage3_ceiling.csv";
    private static final String STAGE_3_FLOOR_FILE_PATH = "stage/stage3_floor.csv";
    private static final String STAGE_4_CEILING_FILE_PATH = "stage/stage4_ceiling.csv";
    private static final String STAGE_4_FLOOR_FILE_PATH = "stage/stage4_floor.csv";
    private static final String STAGE_5_CEILING_FILE_PATH = "stage/stage5_ceiling.csv";
    private static final String STAGE_5_FLOOR_FILE_PATH = "stage/stage5_floor.csv";
    private static final String STAGE_6_CEILING_FILE_PATH = "stage/stage6_ceiling.csv";
    private static final String STAGE_6_FLOOR_FILE_PATH = "stage/stage6_floor.csv";
    private static final CSVReaderImpl CSV_READER = new CSVReaderImpl();

    private LandscapeDataLoads() { }

    /**
     * Method for the data scraping of prestage files.
     * 
     * @return raw data from the csv files for ceiling and floor
     */
    public static PairImpl<ArrayList<PairImpl<Integer, Behaviour>>, ArrayList<PairImpl<Integer, Behaviour>>> getPrestageData() {
        return new PairImpl<ArrayList<PairImpl<Integer, Behaviour>>, ArrayList<PairImpl<Integer, Behaviour>>>(
                CSV_READER.readCSV(PRESTAGE_CEILING),
                CSV_READER.readCSV(PRESTAGE_FLOOR));
    }

    /**
     * Method for the data scraping of stage 1 files.
     * 
     * @return raw data from the csv files for ceiling and floor
     */
    public static PairImpl<ArrayList<PairImpl<Integer, Behaviour>>, ArrayList<PairImpl<Integer, Behaviour>>> getStage1Data() {
        return new PairImpl<ArrayList<PairImpl<Integer, Behaviour>>, ArrayList<PairImpl<Integer, Behaviour>>>(
                CSV_READER.readCSV(STAGE_1_CEILING_FILE_PATH),
                CSV_READER.readCSV(STAGE_1_FLOOR_FILE_PATH));
    }

    /**
     * Method for the data scraping of stage 2 files.
     * 
     * @return raw data from the csv files for ceiling and floor
     */
    public static PairImpl<ArrayList<PairImpl<Integer, Behaviour>>, ArrayList<PairImpl<Integer, Behaviour>>> getStage2Data() {
        return new PairImpl<ArrayList<PairImpl<Integer, Behaviour>>, ArrayList<PairImpl<Integer, Behaviour>>>(
                CSV_READER.readCSV(STAGE_2_CEILING_FILE_PATH),
                CSV_READER.readCSV(STAGE_2_FLOOR_FILE_PATH));
    }

    /**
     * Method for the data scraping of stage 3 files.
     * 
     * @return raw data from the csv files for ceiling and floor
     */
    public static PairImpl<ArrayList<PairImpl<Integer, Behaviour>>, ArrayList<PairImpl<Integer, Behaviour>>> getStage3Data() {
        return new PairImpl<ArrayList<PairImpl<Integer, Behaviour>>, ArrayList<PairImpl<Integer, Behaviour>>>(
                CSV_READER.readCSV(STAGE_3_CEILING_FILE_PATH),
                CSV_READER.readCSV(STAGE_3_FLOOR_FILE_PATH));
    }

    /**
     * Method for the data scraping of stage 4 files.
     * 
     * @return raw data from the csv files for ceiling and floor
     */
    public static PairImpl<ArrayList<PairImpl<Integer, Behaviour>>, ArrayList<PairImpl<Integer, Behaviour>>> getStage4Data() {
        return new PairImpl<ArrayList<PairImpl<Integer, Behaviour>>, ArrayList<PairImpl<Integer, Behaviour>>>(
                CSV_READER.readCSV(STAGE_4_CEILING_FILE_PATH),
                CSV_READER.readCSV(STAGE_4_FLOOR_FILE_PATH));
    }

    /**
     * Method for the data scraping of stage 5 files.
     * 
     * @return raw data from the csv files for ceiling and floor
     */
    public static PairImpl<ArrayList<PairImpl<Integer, Behaviour>>, ArrayList<PairImpl<Integer, Behaviour>>> getStage5Data() {
        return new PairImpl<ArrayList<PairImpl<Integer, Behaviour>>, ArrayList<PairImpl<Integer, Behaviour>>>(
                CSV_READER.readCSV(STAGE_5_CEILING_FILE_PATH),
                CSV_READER.readCSV(STAGE_5_FLOOR_FILE_PATH));
    }

    /**
     * Method for the data scraping of stage 6 files.
     * 
     * @return raw data from the csv files for ceiling and floor
     */
    public static PairImpl<ArrayList<PairImpl<Integer, Behaviour>>, ArrayList<PairImpl<Integer, Behaviour>>> getStage6Data() {
        return new PairImpl<ArrayList<PairImpl<Integer, Behaviour>>, ArrayList<PairImpl<Integer, Behaviour>>>(
                CSV_READER.readCSV(STAGE_6_CEILING_FILE_PATH),
                CSV_READER.readCSV(STAGE_6_FLOOR_FILE_PATH));
    }
}
