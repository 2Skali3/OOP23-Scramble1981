package scramble.model.map.util.raw;

import scramble.model.map.util.enums.LandBehaviour;
import scramble.model.map.util.enums.TerrainType;

/**
 * The RawData class wraps the state of a single segment of a stage.
 * Within it are its the length, behaviour, terrain type and sequential
 * position.
 */
public class SegmentRawData {

    /**
     * Constant that indicate the value that the height will have if there is no
     * specific height present in the segment.
     */
    public static final int VOID_HEIGHT = -20;

    private final int length;
    private final LandBehaviour behaviour;
    private final TerrainType terrainType;
    private int height;

    /**
     * Constructor for the RawData class. By default the height of the segment is
     * setted at {@link #VOID_HEIGHT}.
     * 
     * @param length    the length of the segment
     * @param behaviour the behaviour that the segment will have
     * @param terrainType  the type of the terrain
     *
     * @see TerrainType
     * @see LandBehaviour
     */
    public SegmentRawData(final Integer length, final LandBehaviour behaviour, final TerrainType terrainType) {
        this.behaviour = behaviour;
        this.length = length;
        this.terrainType = terrainType;
        this.height = VOID_HEIGHT;
    }

    /**
     * Method that tells if the segment has a specified height or not.
     * 
     * @return {@code true} if the segment has a specified height,
     *         {@code false} otherwise
     */
    public boolean hasSpecificHeight() {
        return height != VOID_HEIGHT;
    }

    /**
     * Setter for the height field.
     * 
     * @param height the new height of the segment
     */
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * Getter for the height field.
     * 
     * @return the height of the segment
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Getter for the length field.
     * 
     * @return the length of this segment
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Getter for the behaviour field.
     * 
     * @return the behaviour of this segment
     */
    public LandBehaviour getBehaviour() {
        return this.behaviour;
    }

    /**
     * Getter for the land type field.
     * 
     * @return the terrain type of this field.
     */
    public TerrainType getTerrainType() {
        return this.terrainType;
    }
}
