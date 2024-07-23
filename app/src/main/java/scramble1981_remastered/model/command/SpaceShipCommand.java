package scramble1981_remastered.model.command;

import scramble1981_remastered.view.GamePanel;

public class SpaceShipCommand implements Command{

    private GamePanel gamePanel;
    private int x = 0;
    private int y = 0;

    public SpaceShipCommand(GamePanel _panel, int _x, int _y) {
        this.gamePanel = _panel;
        this.x = _x;
        this.y = _y;
    }

    @Override
    public void execute() {
       gamePanel.moveSpaceship(x,y);
    }

}
