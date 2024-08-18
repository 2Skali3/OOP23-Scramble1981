package scramble.model.map.util.api;

import java.util.List;

/**
 * Interface for a class that will work with csv.
 * 
 * @param <X> type of data to extract from the csv file
 */
public interface CSVReader<X> {
    /**
     * Method for reading csv file.
     * 
     * @param relativePath relative path of the file to read
     * @return csv's data as ArrayList
     */
    List<X> readCSV(String relativePath);
}
