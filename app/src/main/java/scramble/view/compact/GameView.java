package scramble.view.compact;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.Dimension;

import scramble.model.map.utils.LandscapeUtils;

/**
 * Class that extends javax.swing.JFrame. This class is the main view of the
 * game.
 * 
 * @see JFrame
 */
public class GameView extends JFrame {

    private static final long serialVersionUID = 1L;
    /** Width of the window. */
    public static final int WINDOW_WIDTH = 800;
    /** Height of the window. */
    public static final int WINDOW_HEIGHT = LandscapeUtils.NUMBER_OF_SPITE_PER_STAGE_HEIGHT
            * LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE;

    private final JLayeredPane mainPanel;

    private final BackgroundPanel backgroundPanel;
    private final LandscapePanel landscapePanel;
    private final SpaceShipPanel spaceShipPanel;
    private final StartMenu startMenu;
    private final FuelBarPanel fuelBarPanel;

    /** Costructor of the class GameVew. */
    public GameView() {
        this.setTitle("Scramble 1981");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.mainPanel = new JLayeredPane();
        this.mainPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        this.backgroundPanel = new BackgroundPanel();
        this.backgroundPanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.mainPanel.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        this.backgroundPanel.canBeRepaint();

        this.startMenu = new StartMenu();
        this.startMenu.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.mainPanel.add(startMenu, JLayeredPane.PALETTE_LAYER);

        this.landscapePanel = new LandscapePanel();
        this.landscapePanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // SpaceShip panel setup
        this.spaceShipPanel = new SpaceShipPanel();
        spaceShipPanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // FuelBar panel setup
        this.fuelBarPanel = new FuelBarPanel();
        this.fuelBarPanel.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        this.add(mainPanel);
        this.setVisible(true);

    }

    /**
     * Class constructor for defensive copy.
     * 
     * @param view the game view to copy
     */
    public GameView(final GameView view) {

        super();
        this.mainPanel = view.getMainPanel();
        this.backgroundPanel = view.getBackgroundPanel();
        this.startMenu = view.getStartMenu();
        this.landscapePanel = view.getLandscapePanel();
        this.spaceShipPanel = view.getSpaceshipPanel();
        this.fuelBarPanel = view.getFuelBarPanel();

        this.setTitle("Scramble");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(view.getDefaultCloseOperation());
        this.setLocationRelativeTo(null);

    }

    /**
     * Getter of the mainPanel.
     * 
     * @return the mainpanel of this GameView
     */

    @SuppressFBWarnings
    public JLayeredPane getMainPanel() {
        return this.mainPanel;
    }

    /**
     * Getter of backgroundPanel.
     *
     * @return the background panel of this GameView
     */

    @SuppressFBWarnings
    public BackgroundPanel getBackgroundPanel() {
        return this.backgroundPanel;
    }

    /**
     * Getter of FuelBarPanel.
     *
     * @return the background panel of this GameView
     */
    @SuppressFBWarnings
    public FuelBarPanel getFuelBarPanel() {
        return this.fuelBarPanel;
    }

    /**
     * Getter of startMenu.
     * 
     * @return the startmenu panel of this GameView
     */

    @SuppressFBWarnings
    public StartMenu getStartMenu() {
        return this.startMenu;
    }

    /**
     * Getter of landscapePanel.
     * 
     * @return the landscape panel of this GameView
     */

    @SuppressFBWarnings
    public LandscapePanel getLandscapePanel() {
        return this.landscapePanel;
    }

    /**
     * Getter of spaceShipPanel.
     * 
     * @return the spaceship panel of this GameView
     */

    @SuppressFBWarnings
    public SpaceShipPanel getSpaceshipPanel() {
        return this.spaceShipPanel;
    }

    /**
     * Setup of the mainPanel for the start of the game itself.
     */
    public void startGame() {
        this.mainPanel.removeAll();

        this.mainPanel.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        this.landscapePanel.canBeRepaint();

        this.mainPanel.add(landscapePanel, JLayeredPane.PALETTE_LAYER);
        this.landscapePanel.canBeRepaint();

        this.mainPanel.add(spaceShipPanel, JLayeredPane.MODAL_LAYER);
        this.spaceShipPanel.canBeRepaint();

        // POPUP_LAYER
        this.mainPanel.add(fuelBarPanel, JLayeredPane.POPUP_LAYER);
        this.fuelBarPanel.canBeRepaint();

    }

}
