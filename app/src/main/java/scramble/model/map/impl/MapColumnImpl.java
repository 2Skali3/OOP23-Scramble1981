package scramble.model.map.impl;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import scramble.model.common.api.Pair;
import scramble.model.common.impl.PairImpl;
import scramble.model.common.util.BufferedImageManager;
import scramble.model.map.api.MapColumn;
import scramble.model.map.util.LandUtils;
import scramble.model.map.util.enums.LandBehaviour;
import scramble.model.map.util.enums.LandPart;
import scramble.model.map.util.enums.StagePart;
import scramble.model.map.util.enums.TerrainType;
import scramble.utility.Constants;

/**
 * Implementation of the interface {@link MapColumn}.
 */
public class MapColumnImpl implements MapColumn {

    private final TerrainType terrainType;
    private final List<BufferedImage> bufferedImages;
    private final List<MapElement> elements;

    private final LandBehaviour floorBehaviour;
    private final Pair<Integer, Integer> floorPosition;

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
        this.floorBehaviour = floor.getBehaviour();
        this.floorPosition = floor.getPosition();
        this.biHeight = LandUtils.PIXEL_PER_LAND_SPRITE_SIDE;
        this.biWidth = LandUtils.PIXEL_PER_LAND_SPRITE_SIDE;
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
        if (LandUtils.dividePixelPerSprite(x) % 2 == 0) {
            if (stagePart == StagePart.CEILING) {
                return BufferedImageManager.changeColorClockwise(LandUtils.getSprite(LandPart.DARK_BRICK_WALL), 0);
            }
            return BufferedImageManager.changeColorCounterClockwise(LandUtils.getSprite(LandPart.WHITE_SQUARE), 2);
        }

        if (stagePart == StagePart.CEILING) {
            return BufferedImageManager.changeColorCounterClockwise(LandUtils.getSprite(LandPart.WHITE_SQUARE), 2);
        }
        return BufferedImageManager.changeColorClockwise(LandUtils.getSprite(LandPart.DARK_BRICK_WALL), 0);
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

        for (int y = 0; y < LandUtils.multiplyPixelPerSprite(
                Constants.SPRITE_PER_STAGE_HEIGHT); y = LandUtils.addPixelPerSprite(y)) {
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
            if (floor.getY() < LandUtils.multiplyPixelPerSprite(Constants.SPRITE_PER_STAGE_HEIGHT)) {
                this.elements.add(new MapElement(x, LandUtils.addPixelPerSprite(floor.getY()), floor.getWidth(),
                        LandUtils.multiplyPixelPerSprite(Constants.SPRITE_PER_STAGE_HEIGHT)
                                - LandUtils.subPixelPerSprite(floor.getY()),
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
        this.floorPosition.setFirstElement(x);
        for (final MapElement me : elements) {
            me.updateHitBox(x, me.getY());
        }
    }

    /** @inheritDoc */
    @Override
    public int gettWidth() {
        return this.biWidth;
    }

    /** @inheritDoc */
    @Override
    public int getBIstHeight() {
        return this.biHeight;
    }

    /** @inheritDoc */
    @Override
    public LandBehaviour getFloorBehaviour() {
        return this.floorBehaviour;
    }

    /** @inheritDoc */
    @Override
    public Pair<Integer, Integer> getFloorPosition() {
        return new PairImpl<Integer, Integer>(this.floorPosition.getFirstElement(), this.floorPosition.getSecondElement());
    }
}
