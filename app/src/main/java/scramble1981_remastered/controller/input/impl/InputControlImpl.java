package scramble1981_remastered.controller.input.impl;

import scramble1981_remastered.controller.input.api.InputControl;
import scramble1981_remastered.model.command.SpaceShipCommand;
import scramble1981_remastered.view.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class InputControlImpl extends KeyAdapter  {

    private final GamePanel gamePanel;
    private final Timer timer;

    public InputControlImpl(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        // Create a timer to scroll the background
        timer = new Timer(100, e -> gamePanel.scrollBackground());
        timer.start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // switch (key) {
        //     case KeyEvent.VK_UP -> gamePanel.moveSpaceship(0, -10);
        //     case KeyEvent.VK_DOWN -> gamePanel.moveSpaceship(0, 10);
        //     case KeyEvent.VK_LEFT -> gamePanel.moveSpaceship(-10, 0);
        //     case KeyEvent.VK_RIGHT -> gamePanel.moveSpaceship(10, 0);
        // }

        switch (key) {
            case KeyEvent.VK_UP -> gamePanel.sendCommand(new SpaceShipCommand(gamePanel, 0, -10));
            case KeyEvent.VK_DOWN -> gamePanel.sendCommand(new SpaceShipCommand(gamePanel, 0, 10));
            case KeyEvent.VK_LEFT -> gamePanel.sendCommand(new SpaceShipCommand(gamePanel, -10, 0));
            case KeyEvent.VK_RIGHT -> gamePanel.sendCommand(new SpaceShipCommand(gamePanel, 10, 0));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // No action needed on key release for now
    }

    
}
