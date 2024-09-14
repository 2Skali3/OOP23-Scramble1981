package scramble.model.enemy;

/** Enum for the state of the {@link Rocket}. */
public enum RocketState {

    /** {@link RocketImpl} is moving. */
    MOVING,

    /** {@link RocketImpl} is in premoving state. */
    PREMOVE,

    /** {@link RocketImpl} is exploded. */
    EXPLODED;
}
