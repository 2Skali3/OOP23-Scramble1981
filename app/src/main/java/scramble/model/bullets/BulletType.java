package scramble.model.bullets;

/**
 * Represents the different types of bullets that can be fired in the game.
 * Each bullet type has distinct behavior and movement patterns.
 */
public enum BulletType {
    /**
     * A bullet that moves horizontally across the screen.
     * This type is used for straight-line shooting and travels from left to right.
     */
    TYPE_HORIZONTAL,
    /**
     * A bullet that follows a parabolic trajectory and falls downward.
     * This type is used for bombs that hit the ground.
     */
    TYPE_BOMB;
}

