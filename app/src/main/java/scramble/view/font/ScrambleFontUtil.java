package scramble.view.font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * This class handles game font for the writing.
 */
public final class ScrambleFontUtil {

    private static final Logger LOG = Logger.getLogger(ScrambleFontUtil.class.getName());
    /**
     * The original scramble font.
     */
    public static final String PATH = "/font/PressStart2P-vaV7.ttf";

    private ScrambleFontUtil() {
    }

    /**
     * Loads the original font.
     * 
     * @param size the font size
     * @return the font
     */
    public static Font loadFont(final float size) {
        try (InputStream is = ScrambleFontUtil.class.getResourceAsStream(PATH)) {
            final Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(size);
        } catch (FontFormatException | IOException e) {
            LOG.severe("Ops!");
            LOG.severe(e.toString());
            return new Font("Arial", Font.PLAIN, (int) size);
        }
    }

}
