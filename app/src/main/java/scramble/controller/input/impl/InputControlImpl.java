package scramble.controller.input.impl;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
//import java.util.Arrays;
//import java.awt.event.KeyListener;

import scramble.controller.input.api.InputControl;
import scramble.model.command.impl.SpaceShipCommand;
import scramble.model.command.impl.BulletCommand;
import scramble.view.GameView;

/**
 * Implementation of InputControl. Extends KeyAdapter in order to get the
 * necessary key bindings.
 */
public class InputControlImpl extends KeyAdapter implements InputControl {

    private static final int MINUS = -10;
    private static final int PLUS = 10;
    private static final int DELAYBULLET = 300;

    private final GameView gv;
    private final Timer timer;
    private final Timer timerBullet;

    /**
     * Class constructor.
     * 
     * @param gv the game view to control
     */
    public InputControlImpl(final GameView gv) {
        this.gv = new GameView(gv);
        // Create a timer to scroll the background
        timer = new Timer(100, e -> this.gv.getLandscape().scrollBackground());
        // TODO add new timer that moves the bullets
        timerBullet = new Timer(DELAYBULLET, e -> this.gv.getLandscape().moveBullets());
    }

    /** {@inheritDoc} */
    @Override
    public void keyPressed(final KeyEvent e) {
        final int key = e.getKeyCode();

        switch (key) {
        case KeyEvent.VK_UP -> gv.getLandscape().sendCommand(new SpaceShipCommand(gv.getLandscape(), 0, MINUS));
        case KeyEvent.VK_DOWN -> gv.getLandscape().sendCommand(new SpaceShipCommand(gv.getLandscape(), 0, PLUS));
        case KeyEvent.VK_LEFT -> gv.getLandscape().sendCommand(new SpaceShipCommand(gv.getLandscape(), MINUS, 0));
        case KeyEvent.VK_RIGHT -> gv.getLandscape().sendCommand(new SpaceShipCommand(gv.getLandscape(), PLUS, 0));
        case KeyEvent.VK_SPACE -> gv.getLandscape().sendCommandBullet(new BulletCommand(gv.getLandscape()));
        case KeyEvent.VK_ENTER -> {
            timer.start();
            timerBullet.start();
            gv.showLandscape();
        }
        default -> {
            break;
        }
        }
    }

    @Override
    public void keyMapping() {
        // TODO Auto-generated method stub

    }

}
