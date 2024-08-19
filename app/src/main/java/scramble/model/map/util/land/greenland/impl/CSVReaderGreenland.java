package scramble.model.map.util.land.greenland.impl;

import scramble.model.map.util.api.CSVReader;
import scramble.model.map.util.enums.LandBehaviour;
import scramble.model.map.util.enums.TerrainType;
import scramble.model.map.util.raw.SegmentRawData;

/**
 * The class {@code CSVReaderGreenland} is an impelmentaion of the interface
 * {@link CSVReader}.
 * This class is used only for csv that store greenland raw data.
 * 
 * @see CSVReader
 * @see SegmentRawData
 */
public class CSVReaderGreenland extends CSVReader<SegmentRawData> {

    private static final TerrainType LAND_TYPE = TerrainType.GREENLAND;

    /** @inheritDoc */
    @Override
    protected SegmentRawData collectData(final String line) {
        final String[] data = line.split(",");
        final int length = Integer.parseInt(data[0].trim());
        final LandBehaviour behaviour = LandBehaviour.valueOf(data[1].trim());
        return new SegmentRawData(length, behaviour, CSVReaderGreenland.LAND_TYPE);
    }

}
