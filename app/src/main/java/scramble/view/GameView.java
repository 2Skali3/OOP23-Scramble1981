package scramble.view;

import javax.swing.JFrame;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.CardLayout;

/**
 * This class generates a JFrame in order to get a window environment.
 * In this it is shown the start menu, the scoreboard and the game itself.
 * It also switches from and to these components.
 */
public class GameView extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private final Background mainPanelBackground;
    private final StartMenu startMenu;
    private final GamePanel landscape;

    /**
     * Class constructor.
     */
    public GameView() {

        mainPanelBackground = new Background();
        mainPanelBackground.setLayout(new CardLayout());
        startMenu = new StartMenu();
        startMenu.setOpaque(false);
        landscape = new GamePanel();
        landscape.setOpaque(false);

        setTitle("Scramble");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanelBackground.add(startMenu, "StartMenu");
        mainPanelBackground.add(landscape, "GamePanel");
        add(mainPanelBackground);
        setVisible(true);

    }

    /**
     * Class constructor for defensive copy.
     */
    /*
     * public GameView(final GameView view) {
     * 
     * mainPanelBackground = view.getMainPanelBackground();
     * startMenu = view.getStartMenu();
     * landscape = view.getLandscape();
     * 
     * setTitle("Scramble");
     * setSize(WIDTH, HEIGHT);
     * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     * setLocationRelativeTo(null);
     * 
     * }
     */

    /**
     * Getter for the main panel.
     * Supressing Warning for SpotBUgs since a standard JPanel is returned.
     * 
     * @return the main panel
     */
    @SuppressFBWarnings
    public Background getMainPanelBackground() {
        return mainPanelBackground;
    }

    /**
     * Getter for the game panel.
     * 
     * @return the game panel
     */
    public GamePanel getLandscape() {
        /*
         * try {
         * return (GamePanel) landscape.clone();
         * } catch (CloneNotSupportedException e) {
         * return new GamePanel();
         * }
         */
        return landscape;
    }

    /**
     * Getter for the start menu.
     * 
     * @return the start menu
     */
    public StartMenu getStartMenu() {
        /*
         * try {
         * return (StartMenu) startMenu.clone();
         * } catch (CloneNotSupportedException e) {
         * return new StartMenu();
         * }
         */
        return startMenu;
    }

    /**
     * Shows on the frame the start menu.
     */
    public void showStartMenu() {
        final CardLayout cl = (CardLayout) mainPanelBackground.getLayout();
        cl.show(mainPanelBackground, "StartMenu");
    }

    /**
     * Shows on the frame the game level.
     */
    public void showLandscape() {
        final CardLayout cl = (CardLayout) mainPanelBackground.getLayout();
        cl.show(mainPanelBackground, "GamePanel");
    }

}
