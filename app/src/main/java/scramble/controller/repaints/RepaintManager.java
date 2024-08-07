package scramble.controller.repaints;

import scramble.view.compact.GameView;

/**
 * Class that manage the repaint of the GamePanel of the GameView.
 */
public class RepaintManager {

    private final GameView gv;
    private int cicleCounter;
    private static final int CICLE_BEFORE_BACKGROUND_REPAINT = 30;

    /**
     * Costructor of the class RepaitManager.
     * 
     * @param gv the GameView that we want to manage
     */
    public RepaintManager(final GameView gv) {
        this.gv = new GameView(gv);
        this.cicleCounter = 0;
    }

    /**
     * Is the method that manage the repaint of the GameView.
     */
    public void repaintManagement() {
        cicleCounter++;
        this.gv.getLandscapePanel().canBeRepaint();
        this.gv.getSpaceshipPanel().canBeRepaint();
        if (cicleCounter % CICLE_BEFORE_BACKGROUND_REPAINT == 0) {
            this.gv.getBackgroundPanel().canBeRepaint();
            cicleCounter = 0;
        }

        this.gv.getMainPanel().repaint();
    }

}
