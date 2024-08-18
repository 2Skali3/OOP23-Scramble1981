package scramble.model.map.util.land.greenland.raw;

import scramble.model.map.util.enums.LandBehaviour;
import scramble.model.map.util.enums.LandType;

/**
 * The RawData class wraps the state of a single segment of a stage.
 * Within it are its the length, behaviour, terrain type and sequential
 * position.
 */
public class SegmentRawData {

    private final int length;
    private final LandBehaviour behaviour;
    private final LandType landType;

    /**
     * Constructor for the RawData class.
     * 
     * @param length    the length of the segment
     * @param behaviour the behaviour that the segment will have
     * @param landType  the type of the terrain
     *
     * @see LandType
     * @see LandBehaviour
     */
    public SegmentRawData(final Integer length, final LandBehaviour behaviour, final LandType landType) {
        this.behaviour = behaviour;
        this.length = length;
        this.landType = landType;
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
     * @return the landscape type of this field.
     */
    public LandType getLandType() {
        return this.landType;
    }
}
