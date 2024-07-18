package scramble1981_remastered.model.spaceShip;

import scramble1981_remastered.model.common.impl.GameElementImpl;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class SpaceShip extends GameElementImpl {

    private List<BufferedImage> sprites;
    private static final int SPRITES = 8;

    public SpaceShip(final int startX, final int startY, final int width, final int height) {
        super(startX, startY, width, height);
        this.sprites = new ArrayList<>();
        for (int i = 1; i <= SPRITES; i++) {
            try {
                sprites.add(ImageIO.read(getClass().getResource("/ship/ship" + i + ".png")));
            } catch (Exception e) {
                System.out.println("Immagine " + i + "non trovata");
            }
        }
    }

    public List<BufferedImage> getSprites() {
        return this.sprites;
    }

    public void move(int dx, int dy) {
        getLocation().updateLocation(dx, dy);
    }

    public BufferedImage getSprite() {
        Random random = new Random();
        int num = random.nextInt(8);

        return sprites.get(num);
    }
}
