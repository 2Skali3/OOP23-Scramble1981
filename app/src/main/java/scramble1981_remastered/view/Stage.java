package scramble1981_remastered.view;

import javax.swing.*;

import scramble1981_remastered.model.spaceship.Astronave;

import java.awt.*;

public class Stage extends JPanel {

    private Astronave astronave;
    private JLabel label;

    public Stage() {
        this.astronave = new Astronave();
        this.label = new JLabel(new ImageIcon("/ship1.png"));
        this.add(label);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(astronave.getSprite(), 100, 100, 100, 100, null);
    }
}