
package scramble.view.compact;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.Dimension;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.util.LandUtils;
import scramble.model.scores.Scores;
import scramble.utility.Constants;
import scramble.controller.map.MapController;
import scramble.controller.mediator.impl.LogicControllerImpl;

import javax.swing.Timer;

/**
 * Class that extends javax.swing.JFrame. This class is the main view of the
 * game.
 *
 * @see JFrame
 */
public class GameView extends JFrame {

    private static final int CHECKPOINT_OFFSET_X = 40;
    private static final long serialVersionUID = 1L;
    /** Width of the window. */
    public static final int WINDOW_WIDTH = 800;
    /** Height of the window. */
    public static final int WINDOW_HEIGHT = LandUtils.NUMBER_OF_SPITE_PER_STAGE_HEIGHT
            * LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE;

    private final JLayeredPane mainPanel;

    private final BackgroundPanel backgroundPanel;
    private final LandscapePanel landscapePanel;
    private final SpaceShipPanel spaceShipPanel;
    private final BulletsPanel bulletsPanel;
    private final RocketPanel rocketPanel;
    private final StartMenu startMenu;
    private final HUDPanel hudPanel;
    private final FuelTankPanel fuelTankPanel;
    private final LogicControllerImpl logicController;

    private final Timer repaintTimer;

    /** Costructor of the class GameVew. */
    public GameView() {
        this.setTitle("Scramble 1981");
        this.setResizable(false);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.mainPanel = new JLayeredPane();
        this.mainPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        this.backgroundPanel = new BackgroundPanel();
        this.backgroundPanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.mainPanel.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);

        this.startMenu = new StartMenu();
        this.startMenu.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.mainPanel.add(startMenu, JLayeredPane.PALETTE_LAYER);

        this.landscapePanel = new LandscapePanel();
        this.landscapePanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // SpaceShip panel setup
        this.spaceShipPanel = new SpaceShipPanel();
        spaceShipPanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Bullets panel setup
        this.bulletsPanel = new BulletsPanel();
        this.bulletsPanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // FuelBar panel setup
        this.hudPanel = new HUDPanel();
        this.hudPanel.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        // Rocket panel setup
        this.rocketPanel = new RocketPanel(LandscapePanel.getMapController().getFlatFloorPositions());
        rocketPanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // FuelTank panel setup
        this.fuelTankPanel = new FuelTankPanel(LandscapePanel.getMapController().getFlatFloorPositions(),
                hudPanel.getFuelBar());
        fuelTankPanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        this.logicController = new LogicControllerImpl(this);

        this.add(mainPanel);
        this.setVisible(true);

        this.repaintTimer = new Timer(32, e -> {
            mainPanel.repaint();
            this.rocketPanel.setMapX(this.landscapePanel.getCurrentMapX());
            this.bulletsPanel.moveBullets();
            this.fuelTankPanel.setMapX(this.landscapePanel.getCurrentMapX());
        });

        this.backgroundPanel.startTimer();

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
        this.bulletsPanel = view.getBulletsPanel();
        this.hudPanel = view.getHudPanel();
        this.rocketPanel = view.getRocketPanel();
        this.fuelTankPanel = view.getFuelTankPanel();
        this.logicController = view.getLogicController();
        this.repaintTimer = view.getRepaintTimer();

        this.setTitle("Scramble");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(view.getDefaultCloseOperation());
        this.setLocationRelativeTo(null);

    }

    /**
     * Getter for the repaint timer.
     *
     * @return the timer
     */
    private Timer getRepaintTimer() {
        return this.repaintTimer;
    }

    /**
     * Getter for the logic controller implementation.
     *
     * @return the logic controller
     */
    private LogicControllerImpl getLogicController() {
        return this.logicController;
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
     * Getter of HUDPanel.
     *
     * @return the fuel bar panel of this GameView
     */
    @SuppressFBWarnings
    public HUDPanel getHudPanel() {
        return this.hudPanel;
    }

    /**
     * Getter of FuelTankPanel.
     *
     * @return the tanks panel of this GameView
     */
    @SuppressFBWarnings
    public FuelTankPanel getFuelTankPanel() {
        return this.fuelTankPanel;
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
     * Getter of bulletsPanel.
     *
     * @return the bullets panel of this GameView
     */

    @SuppressFBWarnings
    public BulletsPanel getBulletsPanel() {
        return this.bulletsPanel;
    }

    /**
     * Getter of RocketPanel.
     *
     * @return the bullets panel of this GameView
     */
    @SuppressFBWarnings
    public RocketPanel getRocketPanel() {
        return this.rocketPanel;
    }

    /**
     * Setup of the mainPanel for the start of the game itself.
     */
    public void startGame() {

        this.mainPanel.removeAll();

        this.mainPanel.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        this.backgroundPanel.startTimer();

        this.mainPanel.add(landscapePanel, JLayeredPane.PALETTE_LAYER);
        this.landscapePanel.startTimer();

        this.mainPanel.add(spaceShipPanel, JLayeredPane.MODAL_LAYER);
        this.spaceShipPanel.startTimer();

        this.mainPanel.add(bulletsPanel, JLayeredPane.MODAL_LAYER);
        this.bulletsPanel.startTimer();

        this.mainPanel.add(rocketPanel, JLayeredPane.MODAL_LAYER);
        this.rocketPanel.startTimer();

        this.mainPanel.add(fuelTankPanel, JLayeredPane.MODAL_LAYER);
        this.fuelTankPanel.startTimer();

        // POPUP_LAYER
        this.mainPanel.add(hudPanel, JLayeredPane.POPUP_LAYER);
        this.hudPanel.startTimer();

        this.startMenu.stopTimer();

        this.hudPanel.resetStage();

    }

    /** Resets to start menu. */
    public void setStart() {
        this.stopAllPanelTimers();
        Scores.addScore(Scores.getCurrentScore());
        Scores.resetCurrentScore();

        this.mainPanel.removeAll();

        landscapePanel.reset(0);
        spaceShipPanel.getSpaceship()
                .updatePosition(
                        new PairImpl<>(Constants.SPACESHIP_STARTER_POSITION, Constants.SPACESHIP_STARTER_POSITION));

        this.mainPanel.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        this.mainPanel.add(startMenu, JLayeredPane.PALETTE_LAYER);

        hudPanel.getFuelBar().fillFuel();

        this.rocketPanel.setMapX(this.landscapePanel.getCurrentMapX());
        this.rocketPanel.resetRockets();
        this.fuelTankPanel.setMapX(this.landscapePanel.getCurrentMapX());
        this.fuelTankPanel.resetTanks();
        this.startMenu.startTimer();

    }

    /**
     * Resets to the nearest checkpoint.
     *
     * @param restartPos X pos of selected checkpoint
     */
    public void restartFromCheckPoint(final int restartPos) {

        landscapePanel.reset(restartPos);
        spaceShipPanel.getSpaceship()
                .updatePosition(
                        new PairImpl<>(restartPos + Constants.SPACESHIP_STARTER_POSITION,
                                Constants.SPACESHIP_STARTER_POSITION));
        hudPanel.getFuelBar().fillFuel();
        spaceShipPanel.startTimer();
        this.rocketPanel.setMapX(this.landscapePanel.getCurrentMapX());
        this.rocketPanel.resetRockets();
        this.fuelTankPanel.setMapX(this.landscapePanel.getCurrentMapX());
        this.fuelTankPanel.resetTanks();

    }

    /**
     * Calculates nearest checkpoint.
     *
     * @return the checkpoint
     */
    public int returnToCheckPoint() {
        final int size = MapController.getStageStartingX().size();
        for (int i = size - 1; i > 0; i--) {
            if (MapController.getStageStartingX().get(i) * LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE < LandscapePanel
                    .getMapController().getCurrentMapX()) {
                if (i == 1) {
                    return MapController.getStageStartingX().get(i);
                } else {
                    return MapController.getStageStartingX().get(i) - CHECKPOINT_OFFSET_X;
                }
            }
        }
        return MapController.getStageStartingX().get(0);
    }

    /** Stops all the timers of the singular panels inside game view. */
    public void stopAllPanelTimers() {
        this.landscapePanel.stopTimer();
        this.bulletsPanel.stopTimer();
        this.spaceShipPanel.stopTimer();
        this.hudPanel.stopTimer();
        this.rocketPanel.stopTimer();
        this.fuelTankPanel.stopTimer();
    }

    /** Starts all the timers of the singular panels inside game view. */
    public void startAllPanelTimers() {
        this.landscapePanel.startTimer();
        this.bulletsPanel.startTimer();
        this.spaceShipPanel.startTimer();
        this.hudPanel.startTimer();
        this.rocketPanel.startTimer();
        this.fuelTankPanel.startTimer();
    }

    /** Starts repaint timer. */
    public void startRepaintTimer() {
        this.repaintTimer.start();
    }

    /** Stops repaint timer. */
    public void stopRepaintTimer() {
        this.repaintTimer.stop();
    }

}
