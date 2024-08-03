package scramble.view;

import javax.swing.JFrame;

import scramble.model.map.utils.LandscapeUtils;

/**
 * Extension of the class JFrame.
 */
public class GameFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Dimension of the window width.
     */
    public static final int WINDOW_WIDTH = 800;

    /**
     * Constructor of the class GameFrame.
     */
    public GameFrame() {
        setTitle("Scramble 1981");
        setSize(GameFrame.WINDOW_WIDTH,
                LandscapeUtils.NUMBER_OF_SPITE_PER_STAGE_HEIGHT * LandscapeUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        final GamePanelDue panel = new GamePanelDue();
        add(panel);

        setVisible(true);
        panel.startGame();
    }
}
