package scramble.controller.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import scramble.model.spaceship.Directions;
import scramble.controller.command.impl.BulletCommand;
import scramble.controller.command.impl.SpaceShipCommand;
import scramble.model.bullets.BulletType;
import scramble.view.compact.GameView;

/**
 * Implementation of InputControl. Extends KeyAdapter in order to get the
 * necessary key bindings.
 */
public class InputControl extends KeyAdapter {

    private static boolean explPause;
    private final GameView gameView;

    /**
     * Class constructor.
     *
     * @param gameView the game view to control
     */
    public InputControl(final GameView gameView) {
        this.gameView = new GameView(gameView);
    }

    /** {@inheritDoc} */
    @Override
    public void keyPressed(final KeyEvent e) {
        if (!gameView.getSpaceshipPanel().getSpaceship().isHit()) {
            final int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_UP ->
                    gameView.getSpaceshipPanel().sendCommand(
                            new SpaceShipCommand(gameView.getSpaceshipPanel(), Directions.UP));
                case KeyEvent.VK_DOWN ->
                    gameView.getSpaceshipPanel().sendCommand(
                            new SpaceShipCommand(gameView.getSpaceshipPanel(), Directions.DOWN));
                case KeyEvent.VK_LEFT ->
                    gameView.getSpaceshipPanel().sendCommand(
                            new SpaceShipCommand(gameView.getSpaceshipPanel(), Directions.LEFT));
                case KeyEvent.VK_RIGHT ->
                    gameView.getSpaceshipPanel().sendCommand(
                            new SpaceShipCommand(gameView.getSpaceshipPanel(), Directions.RIGHT));
                case KeyEvent.VK_SPACE -> gameView.getBulletsPanel()
                        .sendCommandBullet(new BulletCommand(gameView.getBulletsPanel(),
                                BulletType.TYPE_HORIZONTAL, gameView.getSpaceshipPanel().getSpaceship()));
                case KeyEvent.VK_1 -> gameView.getBulletsPanel()
                        .sendCommandBullet(new BulletCommand(gameView.getBulletsPanel(),
                                BulletType.TYPE_BOMB, gameView.getSpaceshipPanel().getSpaceship()));
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
                gameView.getSpaceshipPanel().getSpaceship().setAbove(false);
                break;
            case KeyEvent.VK_DOWN:
                gameView.getSpaceshipPanel().getSpaceship().setDown(false);
                break;
            case KeyEvent.VK_RIGHT:
                gameView.getSpaceshipPanel().getSpaceship().setRight(false);
                break;
            case KeyEvent.VK_LEFT:
                gameView.getSpaceshipPanel().getSpaceship().setLeft(false);
                break;
            case KeyEvent.VK_ENTER:
                if (!gameView.getGameOverPanel().isOverlayOn()) {
                    gameView.startRepaintTimer();
                    gameView.startGame();
                    break;
                }
            default:
        }
    }

    /**
     * Getter for explosion pause state.
     *
     * @return true if paused
     */
    public static boolean isExplPause() {
        return explPause;
    }

    /**
     * Setter for the boolean describing pause state.
     *
     * @param bool the new value
     */
    public static void setPaused(final boolean bool) {
        explPause = bool;
    }

}
