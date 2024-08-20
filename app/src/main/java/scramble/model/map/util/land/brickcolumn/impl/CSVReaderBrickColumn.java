package scramble.model.map.util.land.brickcolumn.impl;

import scramble.model.map.util.LandUtils;
import scramble.model.map.util.api.CSVReader;
import scramble.model.map.util.enums.LandBehaviour;
import scramble.model.map.util.enums.TerrainType;
import scramble.model.map.util.raw.SegmentRawData;

/**
 * The class {@code CSVReaderBrickColumn} is an impelmentaion of the abstact
 * class
 * {@link CSVReader}.
 * This class is used only for csv that store raw data separated by ','.
 * 
 * @see CSVReader
 * @see SegmentRawData
 */
public class CSVReaderBrickColumn extends CSVReader<SegmentRawData> {

    private static final TerrainType LAND_TYPE = TerrainType.BRICK_COLUMN;
    private static final LandBehaviour BEHAVIOUR = LandBehaviour.BRICK;

    /** @inheritDoc */
    @Override
    protected SegmentRawData collectData(final String line) {
        final String[] data = line.split(",");
        final int length = Integer.parseInt(data[0].trim());
        final int height = LandUtils.NUMBER_OF_SPITE_PER_STAGE_HEIGHT - Integer.parseInt(data[1].trim());
        final SegmentRawData srd = new SegmentRawData(length, CSVReaderBrickColumn.BEHAVIOUR,
                CSVReaderBrickColumn.LAND_TYPE);
        srd.setHeight(height);
        return srd;
    }

}
