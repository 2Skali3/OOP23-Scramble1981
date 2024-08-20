package scramble.model.common.util;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public final class BufferedImageManager {

    private static final double ANCHOR_X = 2;
    private static final double ANCHOR_Y = 2;
    
    public static BufferedImage flipBufferedImageWithDegree(final BufferedImage toModify, final int degrees) {
        final BufferedImage modifiedImage = new BufferedImage(toModify.getWidth(), toModify.getHeight(),
                toModify.getType());
        final Graphics2D g2d = modifiedImage.createGraphics();
        final AffineTransform transform = new AffineTransform();

        transform.rotate(Math.toRadians(degrees), toModify.getWidth() / ANCHOR_X, toModify.getHeight() / ANCHOR_Y);
        g2d.setTransform(transform);
        g2d.drawImage(toModify, 0, 0, null);
        g2d.dispose();

        return modifiedImage;
    }

}
