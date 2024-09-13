package scramble.panel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import scramble.model.map.util.LandUtils;
import scramble.view.compact.LandscapePanel;

class LandscapePanelTest {

    @Test
    void testResetCorrectlyResetsLandscape() {
        // Create a LandscapePanel instance
        final LandscapePanel landscapePanel = new LandscapePanel();

        // Set up initial landscape position
        final int initialPosition = 4 * LandUtils.PIXEL_PER_LAND_SPRITE_SIDE;
        landscapePanel.reset(initialPosition);

        // Verify that the landscape is reset to the given starter position
        assertEquals(initialPosition, landscapePanel.getCurrentMapX());
    }
}
