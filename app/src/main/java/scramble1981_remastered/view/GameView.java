package scramble1981_remastered.view;

import javax.swing.*;

public class GameView extends JFrame {

    private GamePanel landscape = new GamePanel();

    public GameView() {

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(landscape);
        // add(panel);
        setVisible(true);
    }

    public GamePanel getLandscape() {
        return landscape;
    }
}
