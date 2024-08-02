package scramble.model.map.utils.api;

import java.util.ArrayList;

/**
 * Interface for a class that will work with csv files.
 * 
 * @param <X> type of data to extract from the csv file
 */
public interface CSVReader<X> {
    /**
     * Method for reading csv file.
     * 
     * @param fileRelativePath relative path of the file to read
     * @return csv's data as ArrayList
     */
    ArrayList<X> readCSV(String fileRelativePath);
}
