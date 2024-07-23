package scramble1981_remastered.view;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

    private JPanel mainPanel;

    private StartMenu startMenu;
    private GamePanel landscape;

    public GameView() {

        mainPanel = new JPanel(new CardLayout());

        startMenu = new StartMenu();
        landscape = new GamePanel();

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel.add(startMenu, "StartMenu");
        mainPanel.add(landscape, "GamePanel");
        add(mainPanel);
        setVisible(true);

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public GamePanel getLandscape() {
        return landscape;
    }

    public StartMenu getStartMenu() {
        return startMenu;
    }

    public void showStartMenu() {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "StartMenu");
    }

    public void showLandscape() {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "GamePanel");
    }

}
