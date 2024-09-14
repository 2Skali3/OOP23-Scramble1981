package scramble.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import scramble.model.map.impl.MapColumnImpl;
import scramble.model.map.impl.MapElement;
import scramble.model.map.util.LandUtils;
import scramble.model.map.util.enums.LandBehaviour;
import scramble.model.map.util.enums.TerrainType;

class MapColumnTest {

    private static final int POS = 50;

    @Test
    void testConstructorInitializesFieldsCorrectly() {
        final MapElement ceiling = new MapElement(0, 0, LandUtils.PIXEL_PER_LAND_SPRITE_SIDE,
                LandUtils.PIXEL_PER_LAND_SPRITE_SIDE,
                new BufferedImage(LandUtils.PIXEL_PER_LAND_SPRITE_SIDE, LandUtils.PIXEL_PER_LAND_SPRITE_SIDE,
                        BufferedImage.TYPE_INT_ARGB),
                TerrainType.GREENLAND, LandBehaviour.FLAT);
        final MapElement floor = new MapElement(0, POS, LandUtils.PIXEL_PER_LAND_SPRITE_SIDE,
                LandUtils.PIXEL_PER_LAND_SPRITE_SIDE,
                new BufferedImage(LandUtils.PIXEL_PER_LAND_SPRITE_SIDE, LandUtils.PIXEL_PER_LAND_SPRITE_SIDE,
                        BufferedImage.TYPE_INT_ARGB),
                TerrainType.GREENLAND, LandBehaviour.FLAT);
        final int x = 5;
        final TerrainType terrainType = TerrainType.GREENLAND;

        final MapColumnImpl mapColumn = new MapColumnImpl(ceiling, floor, x, terrainType);

        assertEquals(x, mapColumn.getX());
        assertEquals(LandBehaviour.FLAT, mapColumn.getFloorBehaviour());
        assertEquals(POS, mapColumn.getFloorPosition().getSecondElement());
        assertEquals(LandUtils.PIXEL_PER_LAND_SPRITE_SIDE, mapColumn.gettWidth());
        assertEquals(LandUtils.PIXEL_PER_LAND_SPRITE_SIDE, mapColumn.getBIsHeight());
        assertFalse(mapColumn.getElements().isEmpty());
        assertFalse(mapColumn.getBIs().isEmpty());
    }

    @Test
    void testMapElementInMapColumnWhenBrickWall() {
        final MapElement ceiling = new MapElement(0, 40, 10, 10, new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB),
                TerrainType.BRICK_COLUMN, LandBehaviour.FLAT);
        final MapElement floor = new MapElement(0, 20, 10, 10, new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB),
                TerrainType.BRICK_COLUMN, LandBehaviour.FLAT);
        final int x = 5;
        final TerrainType terrainType = TerrainType.BRICK_COLUMN;

        final MapColumnImpl mapColumn = new MapColumnImpl(ceiling, floor, x, terrainType);

        assertNotEquals(terrainType, mapColumn);
        assertNotEquals(0, mapColumn.getElements().size());

        assertEquals(4, mapColumn.getElements().size());
    }

    @Test
    void testMapElementInMapColumnWhenGreenLand() {
        final MapElement ceiling = new MapElement(0, 40, 10, 10, new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB),
                TerrainType.GREENLAND, LandBehaviour.FLAT);
        final MapElement floor = new MapElement(0, 20, 10, 10, new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB),
                TerrainType.GREENLAND, LandBehaviour.FLAT);
        final int x = 5;
        final TerrainType terrainType = TerrainType.GREENLAND;

        final MapColumnImpl mapColumn = new MapColumnImpl(ceiling, floor, x, terrainType);

        assertNotEquals(terrainType, mapColumn);
        assertNotEquals(0, mapColumn.getElements().size());

        assertEquals(2, mapColumn.getElements().size());
    }

    @Test
    void testGetFloorBehaviourReturnsCorrectLandBehaviour() {
        // Create MapElement instances for ceiling and floor
        final MapElement ceiling = new MapElement(0, 0, 10, 10, new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB),
                TerrainType.GREENLAND, LandBehaviour.DW);
        final MapElement floor = new MapElement(0, 20, 10, 10, new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB),
                TerrainType.GREENLAND, LandBehaviour.FLAT);

        // Set x coordinate and terrain type
        final int x = 5;
        final TerrainType terrainType = TerrainType.GREENLAND;

        // Create MapColumnImpl instance
        final MapColumnImpl mapColumn = new MapColumnImpl(ceiling, floor, x, terrainType);

        // Test if getFloorBehaviour returns the correct LandBehaviour
        assertEquals(LandBehaviour.FLAT, mapColumn.getFloorBehaviour());
    }

}
