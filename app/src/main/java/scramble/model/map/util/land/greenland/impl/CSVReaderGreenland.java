package scramble.model.map.util.land.greenland.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import scramble.model.map.util.api.CSVReader;
import scramble.model.map.util.enums.LandBehaviour;
import scramble.model.map.util.enums.LandType;
import scramble.model.map.util.land.greenland.raw.SegmentRawData;

/**
 * The class {@code CSVReaderGreenland} is an impelmentaion of the interface
 * {@link CSVReader}.
 * This class is used only for csv that store greenland raw data.
 * 
 * @see CSVReader
 * @see SegmentRawData
 */
public class CSVReaderGreenland implements CSVReader<SegmentRawData> {

    private static final LandType LAND_TYPE = LandType.GREENLAND;
    private static final Logger LOG = Logger.getLogger(CSVReaderGreenland.class.getName());

    /** @inheritDoc */
    @Override
    public List<SegmentRawData> readCSV(final String relativePath) {
        final List<SegmentRawData> dataCollected = new ArrayList<>();
        final ClassLoader classLoader = CSVReaderGreenland.class.getClassLoader();

        int tot = 0;

        try (InputStream inputStream = classLoader.getResourceAsStream(relativePath);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line = reader.readLine();
            while (line != null) {
                final String[] parts = line.split(",");
                final int length = Integer.parseInt(parts[0].trim());
                final LandBehaviour behaviour = LandBehaviour.valueOf(parts[1].trim());
                tot += length;
                dataCollected.add(new SegmentRawData(length, behaviour, CSVReaderGreenland.LAND_TYPE));
                line = reader.readLine();
            }
            System.out.println(tot);
        } catch (IOException e) {
            /* to-do: creazione messaggio di errore */
            LOG.severe("Ops!");
            LOG.severe(e.toString());
        }
        return dataCollected;
    }

}
