package scramble.model.common.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


/**
 * The utility class {@code BufferedImageManager} is a class that contains
 * usefull
 * methods for {@link BufferedImge} manipulation.
 */
public final class BufferedImageManager {

    private static final double ANCHOR_X = 2;
    private static final double ANCHOR_Y = 2;

    private BufferedImageManager() {
    }

    /**
     * Method that it is used to take a transparent {@link BufferedImage}.
     * @param width the width of the transparent image
     * @param height the height of the transparent image
     * @return a {@link BufferedImage} having the {@code width} and {@code height} specified
     */
    public static BufferedImage transparentBufferedImage(final int width, final int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    /**
     * The method {@code rotateBufferedImageWithDegree} is used to rotate a
     * {@link BufferedImage} by a certain degree.
     * 
     * @param toModify the {@link BufferedImage} that we want to rotate
     * @param degrees  the number of degree
     * @return the {@link BufferedImage} rotated
     */
    public static BufferedImage rotateBufferedImageWithDegree(final BufferedImage toModify, final int degrees) {
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

    /**
     * The method {@code exchangeRedWithGreen} exchanges the red rgb component of a
     * {@link BufferedImage} with its green rgb component.
     * 
     * @param toExchange the {@link BufferedImage} that needs to exchange green and
     *                   red rgb component
     * @return the {@link BufferedImage} with red and green rgb components exchanged
     */
    public static BufferedImage exchangeRedWithGreen(final BufferedImage toExchange) {
        final BufferedImage bi = cloneBufferedImage(toExchange);
        for (int y = 0; y < bi.getHeight(); y++) {
            for (int x = 0; x < bi.getWidth(); x++) {
                final int rgb = bi.getRGB(x, y);
                final Color color = new Color(rgb);
                final Color newColor = new Color(color.getGreen(), color.getRed(), color.getBlue());
                bi.setRGB(x, y, newColor.getRGB());
            }
        }
        return bi;
    }

    /**
     * The method {@code substituteGreenWithPurple} substitutes green dominant pixel
     * of a {@link BufferedImage} with purple pixel. The other pixel will be painted
     * black.
     * 
     * @param toSubstitute the {@link BufferedImage} that needs to substitute green
     *                     with purple
     * @return a {@link BufferedImage} with green substituted with purple
     */
    public static BufferedImage substituteGreenWithPurple(final BufferedImage toSubstitute) {
        final BufferedImage bi = cloneBufferedImage(toSubstitute);
        final int threshold = 200;
        final Color purple = new Color(120, 0, 200);
        final Color black = new Color(0, 0, 0);

        for (int y = 0; y < bi.getHeight(); y++) {
            for (int x = 0; x < bi.getWidth(); x++) {
                final int rgb = bi.getRGB(x, y);
                final Color color = new Color(rgb);

                if (color.getGreen() > threshold) {
                    bi.setRGB(x, y, purple.getRGB());
                } else {
                    bi.setRGB(x, y, black.getRGB());
                }
            }
        }
        return bi;
    }

    /**
     * The method {@code cloneBufferedImage} clone a {@link BufferedImage}.
     * 
     * @param sourceBufferedImage the {@link BufferedImage} that needs to be cloned
     * @return a new {@link BufferedImage} with all the characteristics of the
     *         {@code sourceBufferedImage}
     */
    public static BufferedImage cloneBufferedImage(final BufferedImage sourceBufferedImage) {
        final BufferedImage clone = new BufferedImage(sourceBufferedImage.getWidth(), sourceBufferedImage.getHeight(),
                sourceBufferedImage.getType());
        final Graphics2D g2d = clone.createGraphics();
        g2d.drawImage(sourceBufferedImage, 0, 0, null);
        g2d.dispose();
        return clone;
    }

    /**
     * The method {@code substitutePurpleWithRed} substitutes purple dominant pixel
     * of a {@link BufferedImage} with red pixel.
     * 
     * @param toSubstitute the {@link BufferedImage} that needs to substitute green
     *                     with purple
     * @param pixelWidth   the pixel width that will be chacked
     * @return a {@link BufferedImage} with purple substituted with red
     */
    public static BufferedImage substitutePurpleWithRed(final BufferedImage toSubstitute, final float pixelWidth) {
        final BufferedImage bi = cloneBufferedImage(toSubstitute);
        final int threshold = 200;
        final Color red = new Color(120, 0, 0);

        for (int y = 0; y < bi.getHeight(); y++) {
            for (int x = 0; x < pixelWidth; x++) {
                final int rgb = bi.getRGB(x, y);
                final Color color = new Color(rgb);

                if (color.getBlue() > threshold) {
                    bi.setRGB(x, y, red.getRGB());
                }
            }
        }
        return bi;
    }

}
