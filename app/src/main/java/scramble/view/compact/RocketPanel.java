package scramble.view.compact;

import java.awt.Graphics;
import javax.swing.Timer;

import scramble.model.command.impl.RocketCommand;
import scramble.model.common.impl.PairImpl;
import scramble.model.enemy.RocketImpl;
import scramble.model.spaceship.SpaceShip;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class RocketPanel extends GamePanel {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(SpaceShip.class.getName());

    private static final int STARTER_POSITION_X = 850;
    private static final int STARTER_POSITION_Y = 250;

    private static final int ROCKET_HEIGHT = 34;
    private static final int ROCKET_WIDTH = 25;

    private transient RocketImpl rocket;
    private final Timer updateTimer;

    public RocketPanel() {
        this.rocket = new RocketImpl(STARTER_POSITION_X, STARTER_POSITION_Y, ROCKET_WIDTH, ROCKET_HEIGHT);
        this.setOpaque(false);
        updateTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                update();
            }
        });
        updateTimer.start();
    }

    @Override
    protected void drawPanel(final Graphics g) {
        if (rocket.getSprite() != null) {
            g.drawImage(rocket.getSprite(), rocket.getPosition().getFirstElement(),
                    rocket.getPosition().getSecondElement(), rocket.getWidth(), rocket.getHeight(), null);
        }
        rocket.drawHitBox(g);
        this.canNotBeRepaint();
    }

    public RocketImpl getRocket() {
        RocketImpl temp = rocket;
        try {
            temp = rocket.clone();
            this.rocket = temp;
        } catch (CloneNotSupportedException e) {
            LOG.severe("Ops!");
            LOG.severe(e.toString());
        }
        return temp;
    }

    public void sendCommand(final RocketCommand command) {
        command.execute();
    }

    public void moveRocket() {

        this.canBeRepaint();
        repaint();
    }

    public void update() {
        final PairImpl<Integer, Integer> location = rocket.getPosition();
        final int rocketX = location.getFirstElement();
        final int rocketY = location.getSecondElement();
        final int ySpeed = 10;

        if (rocketX < getWidth() / 2 && rocketX >= 0 && rocketY + ySpeed <= getHeight() && rocketY + ySpeed >= 0) {
            rocket.move();
        }

        final int minX = 0;
        final int maxX = getWidth() / 2;
        final int minY = 0;
        final int maxY = getHeight();

        // Condizioni per il movimento orizzontale (asse X)
        if (rocketX >= minX && rocketX <= maxX) {
            rocket.move();
        }

        if (rocketY + ySpeed >= minY && rocketY + ySpeed <= maxY) {
            rocket.move();
        }

    }
}
