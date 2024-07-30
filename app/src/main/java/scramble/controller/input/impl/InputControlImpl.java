package scramble.controller.input.impl;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
//import java.util.Arrays;
//import java.awt.event.KeyListener;

import scramble.controller.input.api.InputControl;
import scramble.model.command.impl.SpaceShipCommand;
import scramble.view.GameView;

/**
 * Implementation of InputControl.
 * Extends KeyAdapter in order to get the necessary key bindings.
 */
public class InputControlImpl extends KeyAdapter implements InputControl {

    private static final int MINUS = -10;
    private static final int PLUS = 10;

    private final GameView gv;
    private final Timer timer;

    /**
     * Class constructor.
     * 
     * @param gv the game view to control
     */
    public InputControlImpl(final GameView gv) {
        this.gv = new GameView(gv);
        // Create a timer to scroll the background
        timer = new Timer(100, e -> this.gv.getLandscape().scrollBackground());
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
            case KeyEvent.VK_ENTER -> {
                timer.start();
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
