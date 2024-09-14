package scramble.controller.input.impl;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import scramble.model.command.impl.SpaceShipCommand;
import scramble.model.spaceship.Directions;
import scramble.model.bullets.BulletType;
import scramble.model.command.impl.BulletCommand;

import scramble.view.compact.GameView;

/**
 * Implementation of InputControl. Extends KeyAdapter in order to get the
 * necessary key bindings.
 */
public class InputControl extends KeyAdapter {

    private final GameView gv;
    private static boolean explPause;

    /**
     * Class constructor.
     *
     * @param gv the game view to control
     */
    public InputControl(final GameView gv) {
        this.gv = new GameView(gv);
    }

    /** {@inheritDoc} */
    @Override
    public void keyPressed(final KeyEvent e) {
        if (!gv.getSpaceshipPanel().getSpaceship().isHit()) {
            final int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_UP ->
                    gv.getSpaceshipPanel().sendCommand(
                            new SpaceShipCommand(gv.getSpaceshipPanel(), Directions.UP));
                case KeyEvent.VK_DOWN ->
                    gv.getSpaceshipPanel().sendCommand(
                            new SpaceShipCommand(gv.getSpaceshipPanel(), Directions.DOWN));
                case KeyEvent.VK_LEFT ->
                    gv.getSpaceshipPanel().sendCommand(
                            new SpaceShipCommand(gv.getSpaceshipPanel(), Directions.LEFT));
                case KeyEvent.VK_RIGHT ->
                    gv.getSpaceshipPanel().sendCommand(
                            new SpaceShipCommand(gv.getSpaceshipPanel(), Directions.RIGHT));
                case KeyEvent.VK_SPACE -> gv.getBulletsPanel()
                        .sendCommandBullet(new BulletCommand(gv.getBulletsPanel(),
                                BulletType.TYPE_HORIZONTAL, gv.getSpaceshipPanel().getSpaceship()));
                case KeyEvent.VK_1 -> gv.getBulletsPanel()
                        .sendCommandBullet(new BulletCommand(gv.getBulletsPanel(),
                                BulletType.TYPE_BOMB, gv.getSpaceshipPanel().getSpaceship()));
                case KeyEvent.VK_ENTER -> {
                    gv.startRepaintTimer();
                    gv.getHudPanel().startTimer();
                    gv.startGame();
                }
                default -> {
                    break;
                }
            }
        }
    }

    /** @InheritDoc */
    @Override
    public void keyReleased(final KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                gv.getSpaceshipPanel().getSpaceship().setAbove(false);
                break;
            case KeyEvent.VK_DOWN:
                gv.getSpaceshipPanel().getSpaceship().setDown(false);
                break;
            case KeyEvent.VK_RIGHT:
                gv.getSpaceshipPanel().getSpaceship().setRight(false);
                break;
            case KeyEvent.VK_LEFT:
                gv.getSpaceshipPanel().getSpaceship().setLeft(false);
                break;
            default:
        }
    }

    /**
     * Setter for the boolean describing pause state.
     *
     * @param bool the new value
     */
    public static void setPaused(final boolean bool) {
        explPause = bool;
    }

    /**
     * Getter for explosion pause state.
     *
     * @return true if paused
     */
    public static boolean isExplPause() {
        return explPause;
    }

}
