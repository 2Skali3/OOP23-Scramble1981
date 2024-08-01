package scramble.model.spaceShip;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Astronave {

    private BufferedImage sprite;

    public Astronave() {
        try {
            this.sprite = ImageIO.read(getClass().getResource("/ship1.png"));
        } catch (Exception e) {
            System.out.println("non trovato");
        }
    }

    public BufferedImage getSprite() {
        return this.sprite;
    }
}
