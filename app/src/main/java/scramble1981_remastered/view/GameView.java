package scramble1981_remastered.view;

import javax.swing.*;

public class GameView extends JFrame {

    private Stage panel;

    public GameView() {
        this.panel = new Stage();
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(panel);
        setVisible(true);
    }
    // JLabel shiplabel = new JLabel(new ImageIcon());
}
