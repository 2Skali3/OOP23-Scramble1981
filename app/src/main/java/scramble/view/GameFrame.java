package scramble.view;

import javax.swing.JFrame;

import scramble.model.map.utils.LandscapeUtils;;

public class GameFrame extends JFrame {
    final static int WINDOW_WIDTH = 800;

    public GameFrame()
    {
        setTitle("Scramble 1981");
        setSize(GameFrame.WINDOW_WIDTH, LandscapeUtils.NUMBER_OF_SPITE_PER_STAGE_HEIGHT * LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        GamePanel panel = new GamePanel();
        add(panel);
        
        setVisible(true);
        panel.startGame();
    }
}