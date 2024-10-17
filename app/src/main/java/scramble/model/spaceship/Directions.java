package scramble.model.spaceship;

/** Enum for the possible directions of the spaceship. */
public enum Directions {

    /** UP. */
    UP(0),
    /** DOWN. */
    DOWN(1),
    /** RIGHT. */
    RIGHT(2),
    /** LEFT. */
    LEFT(3);

    private final int num;

    /**
     * Getter for the enum value.
     * 
     * @return an int
     */
    public int getNum() {
        return num;
    }

    Directions(final int num) {
        this.num = num;
    }

}
