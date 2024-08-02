package scramble.model.map.utils.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.utils.api.CSVReader;

/**
 * Implementation of the CSVReader interface.
 * 
 * @see CSVReader
 */
public class CSVReaderImpl implements CSVReader<PairImpl<Integer, CSVReaderImpl.Behaviour>> {
    /**
     * enum that describes the behaviour of the Landscape.
     */
    public enum Behaviour {
        /**
         * Flat landscape.
         */
        FLAT,
        /**
         * Ascending landscape.
         */
        UP,
        /**
         * Descending landscape.
         */
        DW,
        /**
         * Summit landscape.
         */
        SUMMIT,
        /**
         * Landscape not present.
         */
        EMPTY
    }

    /**
     * enum that describes the elements that form the landscape.
     */
    public enum StageComponent {
        /**
         * Floor part.
         */
        FLOOR,
        /**
         * Ceiling part.
         */
        CEILING
    }

    /**
     * @inheritDoc
     */
    @Override
    public ArrayList<PairImpl<Integer, Behaviour>> readCSV(final String fileRelativePath) {
        ArrayList<PairImpl<Integer, Behaviour>> dataLandscape = new ArrayList<>();
        ClassLoader classLoader = CSVReaderImpl.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileRelativePath);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            Behaviour behaviour;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int length = Integer.parseInt(parts[0].trim());
                behaviour = Behaviour.valueOf(parts[1].trim());
                dataLandscape.add(new PairImpl<Integer, CSVReaderImpl.Behaviour>(length, behaviour));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataLandscape;
    }
}
