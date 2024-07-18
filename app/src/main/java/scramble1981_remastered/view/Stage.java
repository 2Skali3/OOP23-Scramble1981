package scramble1981_remastered.view;

import javax.swing.*;

import scramble1981_remastered.model.spaceShip.SpaceShip;

import java.awt.*;

public class Stage extends JPanel {

    private SpaceShip astronave;
    private JLabel label;

    public Stage() {
        this.astronave = new SpaceShip(100, 100, 100, 50);
        this.label = new JLabel(new ImageIcon("/ship1.png"));
        this.add(label);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(astronave.getSprite(), 100, 100, 100, 100, null);
    }
}