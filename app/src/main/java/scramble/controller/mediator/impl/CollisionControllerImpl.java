package scramble.controller.mediator.impl;

import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.event.ActionListener;

import scramble.controller.input.impl.InputControlImpl;
import scramble.controller.map.MapController;
import scramble.controller.mediator.api.CollisionController;
import scramble.view.compact.GameView;
import scramble.view.compact.LandscapePanel;
import scramble.view.compact.SpaceShipPanel;

/**
 * This class handles the Collision on the map.
 * It implements the interface designed and uses game view to handle
 * the game elements.
 */
public class CollisionControllerImpl implements CollisionController {

    private final LandscapePanel lp;
    private final SpaceShipPanel ssp;
    private final GameView gv;
    private final Timer timer;

    /**
     * Class constructor.
     * 
     * @param gv the game view
     */
    public CollisionControllerImpl(final GameView gv) {
        this.gv = new GameView(gv);
        this.ssp = gv.getSpaceshipPanel();
        this.lp = gv.getLandscapePanel();
        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                touchedGround();
            }
        });
    }

    /** {@inheritDoc} */
    @Override
    public void touchedGround() {
        if (ssp.getSpaceship().checkGroundCollision(lp.getColumns())) {
            // DEVE SUCCEDERE QUI
            timer.stop();
            InputControlImpl.setPaused(true);
            final Timer delayTimer = new Timer(3500, new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    if (gv.getGLoopController().isGameOver()) {
                        gv.setStart();
                    } else {
                        gv.getGLoopController().lostLife();
                        gv.restartFromCheckPoint(returnToCheckPoint());
                    }
                    timer.start();
                    InputControlImpl.setPaused(false);
                    ssp.getSpaceship().setHit(false);
                }
            });
            delayTimer.setRepeats(false); // Il timer deve eseguire l'azione solo una volta
            delayTimer.start();
        }
    }

    private int returnToCheckPoint() {
        final int size = MapController.getStageStartingX().size();
        for (int i = size - 1; i > 0; i--) {
            if (gv.getSpaceshipPanel().getSpaceship().getPosition().getFirstElement() > MapController
                    .getStageStartingX().get(i)) {
                return MapController.getStageStartingX().get(i);
            }
        }
        return MapController.getStageStartingX().get(0);
    }

    /** Initialise the timer for collision checking. */
    public void init() {
        timer.start();
    }

}
