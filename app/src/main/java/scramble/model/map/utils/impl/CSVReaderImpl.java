package scramble.model.map.utils.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.utils.api.CSVReader;
import scramble.model.map.utils.enums.LandscapeBehaviour;

/**
 * Implementation of the CSVReader interface.
 * 
 * @see CSVReader
 */
public class CSVReaderImpl implements CSVReader<PairImpl<Integer, LandscapeBehaviour>> {
    /**
     * @inheritDoc
     */
    @Override
    public ArrayList<PairImpl<Integer, LandscapeBehaviour>> readCSV(final String fileRelativePath) {
        ArrayList<PairImpl<Integer, LandscapeBehaviour>> dataLandscape = new ArrayList<>();
        ClassLoader classLoader = CSVReaderImpl.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileRelativePath);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            LandscapeBehaviour behaviour;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int length = Integer.parseInt(parts[0].trim());
                behaviour = LandscapeBehaviour.valueOf(parts[1].trim());
                dataLandscape.add(new PairImpl<Integer, LandscapeBehaviour>(length, behaviour));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataLandscape;
    }
}
