package scramble.model.command.impl;

import scramble.model.command.api.Command;
import scramble.view.GamePanel;

/**
 * Implementation of Command interface.
 * handles the player (ship) commands and its screen movements.
 */
public final class SpaceShipCommand implements Command {

    private final GamePanel gamePanel;
    private final int x;
    private final int y;

    /**
     * Class constructor.
     * 
     * @param panel the game panel to update
     * @param x     movement on the x axis
     * @param y     movement on the y axis
     */
    public SpaceShipCommand(final GamePanel panel, final int x, final int y) {
        this.gamePanel = panel/* new GamePanel(panel) */;
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute() {
        gamePanel.moveSpaceship(x, y);
    }

}
