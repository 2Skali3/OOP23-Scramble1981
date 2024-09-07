package scramble.utility;

import scramble.model.bullets.BulletType;
import scramble.model.common.impl.PairImpl;

import java.util.Map;

/*//da fare la mappa in controller (parte di francesco)
//si può cambiare i nomi delle costanti senza problemi basta che poi venga anche aggiornato nei vari file
//bisogna migliorare i commenti
//secondo me nei commenti vanno tolti i path di dove viene usato
(anche la classe che utilizza le costanti). In questo momento li ho lasciati per noi
//i nomi delle costanti mi piacevano di più la prima volta ma sono troppo creativo. Serve FEDERICO!!!

potremmo pensare che alcune costanti non ci servono in questo file perchè non
vanno cambiate spesso(anche mai tipo num_sprite) e a certe classi
non può fregare di medo di certe costanti (a spaceship cosa gliene frega di bomb_sprtite_num).
Magari dato che non va mai cambiato e che non serve da nessuna parte
potremmo decidere di spostarle nel file della classe che la usa direttamente e tenere solo le
costanti che POTREBBERO essere cambiate nel tempo.

Per ora le metto tutte per chiarezza e non perderne nessuna per strada

//Possiamo decidere di mettere anche gli enum come BulletType e spaceship/Directions
//Mettere le costanti Anchor trovate in model/util BufferedImageManager
//(Lasse) devo mettere le costanti di rocket
//model/map lo lascio a francesco
//controllare se ci sono variabili come decrease amount che non era stato messo come statico
inizialmente ma torna molto utile metterlo in questo file
//in gameView ci sono alcune costanti da riguardare e decidere se sono da spostare`
//LandscapePanel lo lascio a francesco
*/

/** Public class that keeps all Constants toghether. */
public final class Constants {

    // Controller
    /**
     * Amount in ms between each tick of repaint of GV. Used in InputControllerImpl.
     */
    public static final int INPUT_TIMER_SEC = 30;
    /**
     * Max Stages used in some way for the checkpoints in
     * controller/mediator/LogicControllerImpl.
     */
    public static final int MAX_STAGES = 5;
    /**
     * Starting Y position for each CheckPoint. Used in
     * controller/mediator/LogicControllerImpl.
     */
    public static final int CHECKPOINT_Y_POSITION = 50;
    /** Maximum number of Lives. Used in controller/mediator/LogiControllerImpl. */
    public static final int MAX_LIVES = 200;
    /**
     * A counter used to determin repaint. Used in
     * controller/mediator/RepaintManager.
     */
    public static final int CICLE_BEFORE_BACKGROUND_REPAINT = 30;

    // Model
    /**
     * Movement speed on the X axis of the horizontal bullet type. Used in
     * model/bullets/Bullet.
     */
    public static final int XSPEED_HORIZONTAL_BULLET = 20;
    /**
     * Movement speed on the Y axis of the bomb bullet type. Used in
     * model/bullets/Bullet.
     */
    public static final int YSPEED_BOMB = 5;
    /**
     * Movement speed on the X axis of the bomb bullet type. Used in
     * model/bullets/Bullet.
     */
    public static final int XSPEED_BOMB = 5;
    /** Number of Bomb sprites. Used in model/bullets/Bullet. */
    public static final int SPRITE_NUMBER_BOMB = 5;
    /** Number of Bomb Explosion sprites. Used in model/bullets/Bullet. */
    public static final int SPRITE_NUMBER_BOMB_EXPLOSION = 4;
    /**
     * Map created to save sizes of each bullet type. Used in model/bullets/Bullet.
     */
    public static final Map<BulletType, PairImpl<Integer, Integer>> BULLETS_SIZE_MAP = Map.of(
            BulletType.TYPE_HORIZONTAL, new PairImpl<>(3, 3),
            BulletType.TYPE_BOMB, new PairImpl<>(21, 26));

    /** File path for the scores .json saved list. Used in model/scores/Scores. */
    public static final String SCORES_FILE_PATH = "/scores/scores.json";
    /**
     * This number expresses the max number of scores in the scoreBoard. Used in
     * model/scores/Scores.
     */
    public static final int MAX_N_SCORES = 9;
    /** Max amount of fuel. Used in model/spaceship/FuelBar. */
    public static final int MAX_FUEL = 100;
    /** Amount of fuel consumed every tick. Used in model/spaceship/FuelBar. */
    public static final int FUEL_DECREASE_AMOUNT = 1;
    /** Number of Spaceship sprites. Used in model/spaceship/SpaceShip. */
    public static final int SPRITE_SPACESHIP = 8;
    /** Number of Spaceship Explosion sprites. Used in model/spaceship/SpaceShip. */
    public static final int SPRITE_SPACESHIP_EXPLOSION = 4;
    /** Spaceship movement speed. Used in model/spaceship/SpaceShip. */
    public static final int SPACESHIP_SPEED = 2;

    // view
    /**
     * Number of stars generated in the background. Used in
     * view/compact/BackgroundPanel.
     */
    public static final int NUMBER_OF_STAR_IN_BACKGROUND = 100;

    /**
     * Landscape movement speed.
     * Used in view/compact/LandscapePanel and  in view/compact/BulletsPanel.
     */
    public static final int LANDSCAPEX_SPEED = 4; // 4

    /**
     * Scales up the fuel bar for a fitting image on the screen. Used in
     * view/compact/FuelBarPanel.
     */
    public static final int FUELBAR_SCALE_FACTOR = 2;
    /** Starting X position of the spaceship. Used in view/compact/GameView. */
    public static final int SPACESHIP_STARTER_POSITION = 50;
    /** Width of the Spaceship. Used in SpaceShipPanel. */
    public static final int SPACESHIP_WIDTH = 32;
    /** Height of the Spaceship. Used in SpaceShipPanel. */
    public static final int SPACESHIP_HEIGHT = 16;
    /** The original scramble font. Used in view/font/ScrambleFontUtil */
    public static final String FONT_PATH = "/font/PressStart2P-vaV7.ttf";

    private Constants() {
    }
}
