package scramble.model.map.util.raw;

import java.util.List;

import scramble.model.map.util.enums.TerrainType;

import java.util.ArrayList;

/**
 * The class {@code RawData} rappresent a collection of segments of raw data.
 * There are two separate segments collection for floor and ceiling.
 * 
 * @see SegmentRawData
 */
public class RawData {

    private final TerrainType terrainType;
    private final List<SegmentRawData> ceiling;
    private final List<SegmentRawData> floor;

    /**
     * Constructor for the class {@code RawData}.
     * @param terrainType the type of the terraint
     * @see TerrainType
     */
    public RawData(final TerrainType terrainType) {
        this.terrainType = terrainType;
        this.ceiling = new ArrayList<>();
        this.floor = new ArrayList<>();
    }

    /**
     * Setter for the ceiling part of the raw data.
     * 
     * @param ceiling the raw data relative to the ceiling
     * @see SegmentRawData
     */
    public void setCeiling(final List<SegmentRawData> ceiling) {
        this.ceiling.addAll(ceiling);
    }

    /**
     * Setter for the floor part of the raw data.
     * 
     * @param floor the raw data relative to the ceiling
     * @see SegmentRawData
     */
    public void setFloor(final List<SegmentRawData> floor) {
        this.floor.addAll(floor);
    }

    /**
     * Getter for the ceiling part of the raw data.
     * 
     * @return the ceiling segments
     * @see SegmentRawData
     */
    public List<SegmentRawData> getCeiling() {
        return new ArrayList<>(this.ceiling);
    }

    /**
     * Getter for the floor part of the raw data.
     * 
     * @return the floor segment.
     * @see SegmentRawData
     */
    public List<SegmentRawData> getFloor() {
        return new ArrayList<>(this.floor);
    }

    /**
     * Getter for the terrain type of the raw data.
     * 
     * @return the terrain type of the raw data
     * @see TerrainType
     */
    public TerrainType getTerrainType() {
        return this.terrainType;
    }

}
