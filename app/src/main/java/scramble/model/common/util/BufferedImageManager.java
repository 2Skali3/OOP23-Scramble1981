package scramble.model.common.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.function.Predicate;
import java.util.function.Function;

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
     * 
     * @param width  the width of the transparent image
     * @param height the height of the transparent image
     * @return a {@link BufferedImage} having the {@code width} and {@code height}
     *         specified
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
     * The method {@code substitutePurpleWithRed} substitutes purple dominant pixel
     * of a {@link BufferedImage} with red pixel.
     * 
     * @param toSubstitute the {@link BufferedImage} that needs to substitute green
     *                     with purple
     * @param pixelWidth   the pixel width that will be chacked
     * @return a {@link BufferedImage} with purple substituted with red
     */
    public static BufferedImage substitutePurpleWithRed(final BufferedImage toSubstitute, final float pixelWidth) {
        final int threshold = 200;
        final Color red = new Color(120, 0, 0);

        return changeColor(toSubstitute, pixelWidth, c -> c.getBlue() > threshold, c -> red.getRGB(), Color::getRGB);
    }

    /**
     * Mehtod to change the RGB value in a clockwise style.
     * 
     * <p>
     * In the input {@link BufferedImage} the red, green and blue color components
     * will be switched respectevely with the blue, red and green color components
     * of the same {@link BufferedImage}.
     * 
     * @param toChange the {@link BufferedImage} whose color needs to be changed
     * @param added the value that will be added to the color to intensify it
     * @return the {@link BufferedImage} with the color switched
     */
    public static BufferedImage changeColorClockwise(final BufferedImage toChange, final int added) {
        return changeColor(toChange, toChange.getWidth(), c -> true,
                c -> new Color(c.getBlue(), c.getRed(), c.getGreen()).getRGB(), null);
    }

    /**
     * Mehtod to change the RGB value in a counter clockwise cycle.
     * 
     * <p>
     * In the input {@link BufferedImage} the red, green and blue color components
     * will be switched respectevely with the green, blue and red color components
     * of the same {@link BufferedImage}.
     * 
     * @param toChange the {@link BufferedImage} whose color needs to be changed
     * @param added the value that will be added to the color to intensify it
     * @return the {@link BufferedImage} with the color switched
     */
    public static BufferedImage changeColorCounterClockwise(final BufferedImage toChange, final int added) {
        return changeColor(toChange, toChange.getWidth(), c -> true,
                c -> new Color(c.getGreen(), c.getBlue(), c.getRed()).getRGB() + added, null);
    }

    private static BufferedImage changeColor(final BufferedImage toChange, final float pixelWidth,
            final Predicate<Color> condition, final Function<Color, Integer> funTrue,
            final Function<Color, Integer> funFalse) {
        final BufferedImage bi = cloneBufferedImage(toChange);
        for (int y = 0; y < bi.getHeight(); y++) {
            for (int x = 0; x < pixelWidth; x++) {
                final int rgb = bi.getRGB(x, y);
                final Color color = new Color(rgb);
                if (condition.test(color)) {
                    bi.setRGB(x, y, funTrue.apply(color));
                } else {
                    bi.setRGB(x, y, funFalse.apply(color));
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
}
