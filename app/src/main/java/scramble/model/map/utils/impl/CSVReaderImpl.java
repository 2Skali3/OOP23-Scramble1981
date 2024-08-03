package scramble.model.map.utils.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.utils.api.CSVReader;
import scramble.model.map.utils.enums.LandscapeBehaviour;

/**
 * Implementation of the CSVReader interface.
 * 
 * @see CSVReader
 */
public class CSVReaderImpl implements CSVReader<PairImpl<Integer, LandscapeBehaviour>> {

    private static final Logger LOG = Logger.getLogger(CSVReaderImpl.class.getName());

    /**
     * @inheritDoc
     */
    @Override
    public List<PairImpl<Integer, LandscapeBehaviour>> readCSV(final String fileRelativePath) {
        final List<PairImpl<Integer, LandscapeBehaviour>> dataLandscape = new ArrayList<>();
        final ClassLoader classLoader = CSVReaderImpl.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileRelativePath);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            LandscapeBehaviour behaviour;
            String line = reader.readLine();
            while (line != null) {
                final String[] parts = line.split(",");
                final int length = Integer.parseInt(parts[0].trim());
                behaviour = LandscapeBehaviour.valueOf(parts[1].trim());
                dataLandscape.add(new PairImpl<Integer, LandscapeBehaviour>(length, behaviour));
                line = reader.readLine();
            }
        } catch (IOException e) {
            LOG.severe("Ops!");
            LOG.severe(e.toString());
        }
        return dataLandscape;
    }
}
