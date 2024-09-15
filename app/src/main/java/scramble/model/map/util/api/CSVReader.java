package scramble.model.map.util.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import scramble.model.map.util.land.brickcolumn.impl.CSVReaderBrickColumn;

/**
 * Interface for a class that will work with csv.
 * 
 * @param <X> type of data to extract from the csv file
 */
public abstract class CSVReader<X> {

    private static final Logger LOG = Logger.getLogger(CSVReader.class.getName());

    /**
     * Method for the inner logic of the data extrapolation of the CSV.
     * 
     * @param line the csv line that contain the data
     * @return the data extracted from the line
     */
    protected abstract X collectData(String line);

    /**
     * Method for reading csv file.
     * 
     * @param relativePath relative path of the file to read
     * @return csv's data as ArrayList
     */
    public List<X> readCSV(final String relativePath) {
        final List<X> dataCollected = new ArrayList<>();
        final ClassLoader classLoader = CSVReaderBrickColumn.class.getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(relativePath);
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line = br.readLine();
            while (line != null) {
                dataCollected.add(collectData(line));
                line = br.readLine();

            }
        } catch (IOException e) {
            /* creazione messaggio di errore */
            LOG.severe("Ops!");
            LOG.severe(e.toString());
        }
        return dataCollected;
    }
}
