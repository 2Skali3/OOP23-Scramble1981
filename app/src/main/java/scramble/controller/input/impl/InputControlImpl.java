package scramble.controller.input.impl;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

import scramble.controller.input.api.InputControl;
import scramble.controller.repaints.RepaintManager;
import scramble.model.command.impl.SpaceShipCommand;
import scramble.model.command.impl.BulletCommand;

import scramble.view.compact.GameView;

/**
 * Implementation of InputControl. Extends KeyAdapter in order to get the
 * necessary key bindings.
 */
public class InputControlImpl extends KeyAdapter implements InputControl {

    private static final int MINUS = -10;
    private static final int PLUS = 10;
    private static final int DELAYBULLET = 100;
    private final Timer timerBullet;
    private static final int SEC = 30;

    private final GameView gv;
    private final Timer timer;
    private final RepaintManager rm;

    /**
     * Class constructor.
     * 
     * @param gv the game view to control
     */
    public InputControlImpl(final GameView gv) {
        this.gv = new GameView(gv);
        this.rm = new RepaintManager(gv);

        timerBullet = new Timer(DELAYBULLET, e -> this.gv.getSpaceshipPanel().moveBullets());
        this.timer = new Timer(SEC, e -> this.rm.repaintManagement());
    }

    /** {@inheritDoc} */
    @Override
    public void keyPressed(final KeyEvent e) {
        final int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_UP ->
                gv.getSpaceshipPanel().sendCommand(new SpaceShipCommand(gv.getSpaceshipPanel(), 0, MINUS));
            case KeyEvent.VK_DOWN ->
                gv.getSpaceshipPanel().sendCommand(new SpaceShipCommand(gv.getSpaceshipPanel(), 0, PLUS));
            case KeyEvent.VK_LEFT ->
                gv.getSpaceshipPanel().sendCommand(new SpaceShipCommand(gv.getSpaceshipPanel(), MINUS, 0));
            case KeyEvent.VK_RIGHT ->
                gv.getSpaceshipPanel().sendCommand(new SpaceShipCommand(gv.getSpaceshipPanel(), PLUS, 0));
            case KeyEvent.VK_SPACE ->
                gv.getSpaceshipPanel().sendCommandBullet(new BulletCommand(gv.getSpaceshipPanel()));
            case KeyEvent.VK_ENTER -> {
                timer.start();
                timerBullet.start();
                gv.startGame();
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
