package scramble1981_remastered.view;

import javax.swing.JFrame;

import scramble1981_remastered.model.map.MapUtils;

public class GameFrame extends JFrame {
    final static int WINDOW_WIDTH = 800;

    public GameFrame()
    {
        setTitle("Scramble 1981");
        setSize(GameFrame.WINDOW_WIDTH, MapUtils.STAGE_HEIGHT * MapUtils.MAP_SPRITE_PX);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        GamePanel panel = new GamePanel();
        add(panel);
        
        setVisible(true);
        panel.startGame();
    }
}