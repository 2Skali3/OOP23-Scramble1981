package scramble.view.compact;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.Dimension;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.util.LandUtils;
import scramble.controller.gameloop.GameLoopController;
import scramble.controller.mediator.impl.CollisionControllerImpl;

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
    public static final int WINDOW_HEIGHT = LandUtils.NUMBER_OF_SPITE_PER_STAGE_HEIGHT
            * LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE;
    private static final int SPACESHIP_STARTER_POSITION = 50;
    private static final int MAX_LIVES = 2;

    private final JLayeredPane mainPanel;

    private final BackgroundPanel backgroundPanel;
    private final LandscapePanel landscapePanel;
    private final SpaceShipPanel spaceShipPanel;
    private final StartMenu startMenu;
    private final FuelBarPanel fuelBarPanel;
    private final CollisionControllerImpl collContr;
    private GameLoopController gLoopController;

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

        this.gLoopController = new GameLoopController(MAX_LIVES);
        this.collContr = new CollisionControllerImpl(this);

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
        this.collContr = view.getCollisionController();
        this.gLoopController = view.getGLoopController();

        this.setTitle("Scramble");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(view.getDefaultCloseOperation());
        this.setLocationRelativeTo(null);

    }

    private CollisionControllerImpl getCollisionController() {
        return collContr;
    }

    /**
     * Getter for the GameLoopController.
     * 
     * @return the GameLoopController of the GameView
     */
    public GameLoopController getGLoopController() {
        final GameLoopController temp = new GameLoopController(gLoopController.getLives());
        gLoopController = temp;
        return temp;
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

        collContr.init();
    }

    /** Resets to start menu. */
    public void setStart() {
        this.mainPanel.removeAll();

        landscapePanel.reset(0);
        spaceShipPanel.getSpaceship()
                .updatePosition(new PairImpl<>(SPACESHIP_STARTER_POSITION, SPACESHIP_STARTER_POSITION));

        this.mainPanel.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        this.mainPanel.add(startMenu, JLayeredPane.PALETTE_LAYER);

        gLoopController.resetLives();
    }

    /**
     * Resets to the nearest checkpoint.
     * 
     * @param restartPos X pos of selected checkpoint
     */
    public void restartFromCheckPoint(final int restartPos) {
        landscapePanel.reset(restartPos);
        spaceShipPanel.getSpaceship()
                .updatePosition(new PairImpl<>(restartPos + SPACESHIP_STARTER_POSITION, SPACESHIP_STARTER_POSITION));
        this.landscapePanel.canBeRepaint();
    }

}
