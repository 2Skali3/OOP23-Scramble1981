package scramble.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.impl.MapElement;
import scramble.model.map.util.LandUtils;
import scramble.model.map.util.enums.LandBehaviour;
import scramble.model.map.util.enums.TerrainType;

class MapElementTest {

    private static final int UPDATED_X_POSITION = 50;
    private static final int UPDATED_Y_POSITION = 60;

    @Test
    void testInitializationWithValidParameters() {
        final int x = 0;
        final int y = 0;
        final int width = 30;
        final int height = 40;
        final BufferedImage sprite = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        final TerrainType terrainType = TerrainType.BRICK_COLUMN;
        final LandBehaviour behaviour = LandBehaviour.FLAT;

        final MapElement mapElement = new MapElement(x, y, width, height, sprite, terrainType, behaviour);

        assertEquals(x, mapElement.getX());
        assertEquals(y, mapElement.getY());
        assertEquals(width, mapElement.getWidth());
        assertEquals(height, mapElement.getHeight());
        assertNotNull(mapElement.getSprite());
        assertEquals(terrainType, mapElement.getTerrainType());
        assertEquals(behaviour, mapElement.getBehaviour());
    }

    @Test
    void testUpdatePositionUpdatesCorrectly() {
        // Create MapElement object
        final int x = 0;
        final int y = 0;
        final int width = LandUtils.PIXEL_PER_LAND_SPRITE_SIDE;
        final int height = LandUtils.PIXEL_PER_LAND_SPRITE_SIDE;
        final BufferedImage sprite = new BufferedImage(LandUtils.PIXEL_PER_LAND_SPRITE_SIDE,
                LandUtils.PIXEL_PER_LAND_SPRITE_SIDE, BufferedImage.TYPE_INT_ARGB);
        final TerrainType terrainType = TerrainType.BRICK_COLUMN;
        final LandBehaviour behaviour = LandBehaviour.FLAT;

        final MapElement mapElement = new MapElement(x, y, width, height, sprite, terrainType, behaviour);

        // Update position
        final PairImpl<Integer, Integer> newPosition = new PairImpl<>(UPDATED_X_POSITION, UPDATED_Y_POSITION);
        mapElement.updatePosition(newPosition);

        // Check if position is updated correctly
        assertEquals(newPosition.getFirstElement(), mapElement.getX());
        assertEquals(newPosition.getSecondElement(), mapElement.getY());
    }

    @Test
    void testUpdateHitboxPosition() {
        // Setup
        final int x = 0;
        final int y = 0;
        final int width = 30;
        final int height = 40;
        final BufferedImage sprite = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        final TerrainType terrainType = TerrainType.BRICK_COLUMN;
        final LandBehaviour behaviour = LandBehaviour.BRICK;

        final MapElement mapElement = new MapElement(x, y, width, height, sprite, terrainType, behaviour);

        // Call the method to be tested
        mapElement.updateHitBoxPosition(UPDATED_X_POSITION, UPDATED_Y_POSITION);

        // Assertion
        assertNotEquals(UPDATED_X_POSITION, mapElement.getX());
        assertNotEquals(UPDATED_Y_POSITION, mapElement.getY());

        assertEquals(UPDATED_X_POSITION, mapElement.getHitBox().x);
        assertEquals(UPDATED_Y_POSITION, mapElement.getHitBox().y);
    }

}
