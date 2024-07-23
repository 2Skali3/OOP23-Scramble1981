package scramble1981_remastered.controller.input.impl;

import scramble1981_remastered.controller.input.api.InputControl;
import scramble1981_remastered.model.command.SpaceShipCommand;
import scramble1981_remastered.view.GameView;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class InputControlImpl extends KeyAdapter implements InputControl {

    private final GameView gv;
    private final Timer timer;

    public InputControlImpl(final GameView gv) {
        this.gv = gv;
        // Create a timer to scroll the background
        timer = new Timer(100, e -> gv.getLandscape().scrollBackground());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_UP -> gv.getLandscape().sendCommand(new SpaceShipCommand(gv.getLandscape(), 0, -10));
            case KeyEvent.VK_DOWN -> gv.getLandscape().sendCommand(new SpaceShipCommand(gv.getLandscape(), 0, 10));
            case KeyEvent.VK_LEFT -> gv.getLandscape().sendCommand(new SpaceShipCommand(gv.getLandscape(), -10, 0));
            case KeyEvent.VK_RIGHT -> gv.getLandscape().sendCommand(new SpaceShipCommand(gv.getLandscape(), 10, 0));
            case KeyEvent.VK_ENTER -> {
                timer.start();
                gv.showLandscape();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // No action needed on key release for now
    }

    @Override
    public void keyMapping() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyMapping'");
    }
}
