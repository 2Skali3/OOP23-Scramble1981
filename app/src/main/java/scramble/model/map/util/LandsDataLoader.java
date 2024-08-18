package scramble.model.map.util;

import scramble.model.map.util.land.greenland.impl.CSVReaderGreenland;
import scramble.model.map.util.land.greenland.raw.RawData;

/**
 * Class that contains static methods for the management of the csv files
 * reading for data scraping.
 */
public final class LandsDataLoader {
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
    private static final CSVReaderGreenland CSV_READER_GREENLAND = new CSVReaderGreenland();

    private LandsDataLoader() {
    }

    private static RawData rawDataMaker(final String ceiling, final String floor) {
        final RawData rawData = new RawData();
        rawData.setCeiling(CSV_READER_GREENLAND.readCSV(ceiling));
        rawData.setFloor(CSV_READER_GREENLAND.readCSV(floor));

        return rawData;
    }

    /**
     * Method for the data scraping of prestage files.
     * 
     * @return raw data from the csv files for ceiling and floor
     */
    public static RawData getPrestageData() {
        return LandsDataLoader.rawDataMaker(PRESTAGE_CEILING, PRESTAGE_FLOOR);
    }

    /**
     * Method for the data scraping of stage 1 files.
     * 
     * @return raw data from the csv files for ceiling and floor
     */
    public static RawData getStage1Data() {
        return LandsDataLoader.rawDataMaker(STAGE_1_CEILING_FILE_PATH, STAGE_1_FLOOR_FILE_PATH);
    }

    /**
     * Method for the data scraping of stage 2 files.
     * 
     * @return raw data from the csv files for ceiling and floor
     */
    public static RawData getStage2Data() {
        return LandsDataLoader.rawDataMaker(STAGE_2_CEILING_FILE_PATH, STAGE_2_FLOOR_FILE_PATH);

    }

    /**
     * Method for the data scraping of stage 3 files.
     * 
     * @return raw data from the csv files for ceiling and floor
     */
    public static RawData getStage3Data() {
        return LandsDataLoader.rawDataMaker(STAGE_3_CEILING_FILE_PATH, STAGE_3_FLOOR_FILE_PATH);

    }

    /**
     * Method for the data scraping of stage 4 files.
     * 
     * @return raw data from the csv files for ceiling and floor
     */
    public static RawData getStage4Data() {
        return LandsDataLoader.rawDataMaker(STAGE_4_CEILING_FILE_PATH, STAGE_4_FLOOR_FILE_PATH);
    }

    /**
     * Method for the data scraping of stage 5 files.
     * 
     * @return raw data from the csv files for ceiling and floor
     */
    public static RawData getStage5Data() {
        return LandsDataLoader.rawDataMaker(STAGE_5_CEILING_FILE_PATH, STAGE_5_FLOOR_FILE_PATH);
    }

    /**
     * Method for the data scraping of stage 6 files.
     * 
     * @return raw data from the csv files for ceiling and floor
     */
    public static RawData getStage6Data() {
        return LandsDataLoader.rawDataMaker(STAGE_6_CEILING_FILE_PATH, STAGE_6_FLOOR_FILE_PATH);
    }
}
