package scramble.model.map.impl;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import scramble.model.common.util.BufferedImageManager;
import scramble.model.map.api.MapColumn;
import scramble.model.map.util.LandUtils;
import scramble.model.map.util.enums.LandBehaviour;
import scramble.model.map.util.enums.LandPart;
import scramble.model.map.util.enums.StagePart;
import scramble.model.map.util.enums.TerrainType;

/**
 * Implementation of the interface {@link MapColumn}.
 */
public class MapColumnImpl implements MapColumn {

    private final TerrainType terrainType;
    private final List<BufferedImage> bufferedImages;
    private final List<MapElement> elements;

    private final int biHeight;
    private final int biWidth;

    private int x;

    /**
     * Constructor for the class {@code MapColumnImpl}.
     * 
     * @param ceiling     the ceiling {@link MapElement} of the column
     * @param floor       the floor {@link MapElement} of the column
     * @param x           the coordinate in the x-axis of the column
     * @param terrainType the {@link TerrainType} of the column
     */
    public MapColumnImpl(final MapElement ceiling, final MapElement floor, final int x,
            final TerrainType terrainType) {
        this.x = x;
        this.biHeight = LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE;
        this.biWidth = LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE;
        this.terrainType = terrainType;
        this.bufferedImages = new ArrayList<>();
        this.elements = new ArrayList<>();

        this.fillBufferedImages(ceiling.getY(), floor.getY(), ceiling.getSprite(), floor.getSprite());
        this.fillElements(ceiling, floor);
    }

    private BufferedImage selectBI(final StagePart stagePart) {
        if (this.terrainType == TerrainType.GREENLAND) {
            return LandUtils.getSprite(LandPart.GREEN_SQUARE);
        }
        if (x / LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE % 2 == 0) {
            if (stagePart == StagePart.CEILING) {
                return BufferedImageManager.exchangeRedWithGreen(LandUtils.getSprite(LandPart.DARK_BRICK_WALL));
            }
            return BufferedImageManager.exchangeRedWithGreen(LandUtils.getSprite(LandPart.WHITE_SQUARE));
        }

        if (stagePart == StagePart.CEILING) {
            return BufferedImageManager.exchangeRedWithGreen(LandUtils.getSprite(LandPart.WHITE_SQUARE));
        }
        return BufferedImageManager.exchangeRedWithGreen(LandUtils.getSprite(LandPart.DARK_BRICK_WALL));
    }

    private void fillBufferedImages(final int yCeiling, final int yFloor, final BufferedImage ceiling,
            final BufferedImage floor) {

        final BufferedImage biCeiling = this.selectBI(StagePart.CEILING);
        final BufferedImage biFloor = this.selectBI(StagePart.FLOOR);
        final BufferedImage transparent = BufferedImageManager.transparentBufferedImage(biCeiling.getWidth(),
                biCeiling.getHeight());
        BufferedImage currentBI;

        if (yCeiling < 0) {
            currentBI = transparent;
        } else {
            currentBI = biCeiling;
        }

        for (int y = 0; y < LandUtils.NUMBER_OF_SPITE_PER_STAGE_HEIGHT
                * LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE; y += LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE) {
            if (y == yCeiling) {
                this.bufferedImages.add(BufferedImageManager.cloneBufferedImage(ceiling));
                currentBI = transparent;
            } else if (y == yFloor) {
                this.bufferedImages.add(BufferedImageManager.cloneBufferedImage(floor));
                currentBI = biFloor;
            } else {
                this.bufferedImages.add(currentBI);
            }
        }
    }

    private void fillElements(final MapElement ceiling, final MapElement floor) {
        if (ceiling.getY() >= 0) {
            this.elements.add(ceiling);
        }
        this.elements.add(floor);
        if (this.terrainType == TerrainType.BRICK_COLUMN) {
            if (ceiling.getY() > 0) {
                this.elements.add(new MapElement(x, 0, ceiling.getWidth(), ceiling.getY(),
                        BufferedImageManager.transparentBufferedImage(ceiling.getWidth(), ceiling.getY()),
                        this.terrainType, LandBehaviour.EMPTY));
            }
            if (floor.getY() < LandUtils.NUMBER_OF_SPITE_PER_STAGE_HEIGHT * LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE) {
                this.elements.add(new MapElement(x, floor.getY() + LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE,
                        floor.getWidth(),
                        LandUtils.NUMBER_OF_SPITE_PER_STAGE_HEIGHT * LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE
                                - floor.getY() - LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE,
                        BufferedImageManager.transparentBufferedImage(floor.getWidth(), floor.getY()),
                        this.terrainType, LandBehaviour.EMPTY));

            }
        }
    }

    /** @inheritDoc */
    @Override
    public List<MapElement> getElements() {
        return new ArrayList<>(this.elements);
    }

    /** @inheritDoc */
    @Override
    public List<BufferedImage> getBIs() {
        return new ArrayList<>(this.bufferedImages);
    }

    /** @inheritDoc */
    @Override
    public int getX() {
        return this.x;
    }

    /** @inheritDoc */
    @Override
    public void updateX(final int x) {
        this.x = x;
        this.updateHitBox(x);
    }

    /** @inheritDoc */
    @Override
    public void updateHitBox(final int x) {
        for (final MapElement me : elements) {
            me.updateHitBox(x, me.getY());
        }
    }

    /** @inheritDoc */
    @Override
    public int getBIListWidth() {
        return this.biWidth;
    }

    /** @inheritDoc */
    @Override
    public int getBIListHeight() {
        return this.biHeight;
    }
}
