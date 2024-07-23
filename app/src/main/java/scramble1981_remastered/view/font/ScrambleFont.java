package scramble1981_remastered.view.font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class ScrambleFont {

    public static final String PATH = "/font/PressStart2P-vaV7.ttf";

    public static Font loadFont(float size) {
        try (InputStream is = ScrambleFont.class.getResourceAsStream(PATH)) {
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, (int) size);
        }
    }

}
