package scramble.model.map.impl;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import scramble.model.common.util.BufferedImageManager;
import scramble.model.map.api.MapColumn;
import scramble.model.map.util.LandUtils;
import scramble.model.map.util.enums.LandPart;
import scramble.model.map.util.enums.TerrainType;

/**
 * Implementation of the interface {@link MapColumn}.
 */
public class MapColumnImpl implements MapColumn {

    private final TerrainType terrainType;
    private final int currentX;

    private final List<BufferedImage> ceilingBI;
    private final List<MapElement> ceilingME;

    private final List<BufferedImage> floorBI;
    private final List<MapElement> floorME;

    private final int endCeiling;
    private final int startFloor;

    /**
     * Costruttor of the class {@code MapColumnImpl}.
     * 
     * @param ceilingME  list of the ceiling MapElement
     * @param floorME    list of the floor MapElement
     * @param endCeiling y position of the end of the ceiling
     * @param startFloor y position of the start of the floor
     */
    public MapColumnImpl(final List<MapElement> ceilingME, final List<MapElement> floorME, final int endCeiling,
            final int startFloor) {
        if (ceilingME.get(0).getHeight() > 0) {
            this.ceilingME = new ArrayList<>(ceilingME);
        } else {
            this.ceilingME = new ArrayList<>();
        }
        this.floorME = new ArrayList<>(floorME);
        if (ceilingME.get(0).getTerrainType() == TerrainType.BRICK_COLUMN) {
            this.terrainType = TerrainType.BRICK_COLUMN;
        } else {
            this.terrainType = TerrainType.GREENLAND;
        }
        this.currentX = floorME.get(0).getX();
        this.endCeiling = endCeiling;
        this.startFloor = startFloor;
        this.ceilingBI = new ArrayList<>();
        this.floorBI = new ArrayList<>();
        this.fillCeilingBI();
        this.fillFloorBI();
    }

    private void fillCeilingBI() {
        if (this.terrainType == TerrainType.BRICK_COLUMN) {
            final BufferedImage gs;
            if ((this.currentX / LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE) % 2 == 0) {
                gs = BufferedImageManager.exchangeRedWithGreen(LandUtils.getSprite(LandPart.WHITE_SQUARE));
            } else {
                gs = BufferedImageManager.exchangeRedWithGreen(LandUtils.getSprite(LandPart.DARK_BRICK_WALL));
            }
            for (int y = 0; y < this.endCeiling; y += LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE) {
                this.ceilingBI.add(gs);
            }
        } else {
            for (int y = 0; y < this.endCeiling; y += LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE) {
                this.ceilingBI.add(LandUtils.getSprite(LandPart.GREEN_SQUARE));
            }
        }
    }

    private void fillFloorBI() {
        if (terrainType == TerrainType.BRICK_COLUMN) {
            final BufferedImage gs;
            if ((this.currentX / LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE) % 2 == 0) {
                gs = BufferedImageManager.exchangeRedWithGreen(LandUtils.getSprite(LandPart.WHITE_SQUARE));
            } else {
                gs = BufferedImageManager.exchangeRedWithGreen(LandUtils.getSprite(LandPart.DARK_BRICK_WALL));
            }
            for (int y = this.startFloor
                    + LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE; y < LandUtils.NUMBER_OF_SPITE_PER_STAGE_HEIGHT
                            * LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE; y += LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE) {
                this.floorBI.add(gs);
            }
        } else {
            for (int y = this.startFloor
                    + LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE; y < LandUtils.NUMBER_OF_SPITE_PER_STAGE_HEIGHT
                            * LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE; y += LandUtils.NUMBER_OF_PX_IN_MAP_PER_SPRITE) {
                this.floorBI.add(LandUtils.getSprite(LandPart.GREEN_SQUARE));
            }
        }

    }

    /** {@inheritDoc} */
    @Override
    public List<MapElement> getCeilings() {
        return new ArrayList<>(this.ceilingME);
    }

    /** {@inheritDoc} */
    @Override
    public List<BufferedImage> getBIListCeiling() {
        return new ArrayList<>(this.ceilingBI);
    }

    /** {@inheritDoc} */
    @Override
    public List<MapElement> getFloors() {
        return new ArrayList<>(this.floorME);
    }

    /** {@inheritDoc} */
    @Override
    public List<BufferedImage> getBIListFloor() {
        return new ArrayList<>(this.floorBI);
    }

    /** {@inheritDoc} */
    @Override
    public int getEndYCeiling() {
        return this.endCeiling;
    }

    /** {@inheritDoc} */
    @Override
    public int getStartYFloor() {
        return this.startFloor;
    }

    /** {@inheritDoc} */
    @Override
    public void updateHitBoxX(final int x) {
        for (int i = 0; i < this.ceilingME.size(); i++) {
            this.ceilingME.get(i).updateHitBox(this.ceilingME.get(i).getX() + x, this.ceilingME.get(i).getY());
        }
        for (int i = 0; i < this.floorME.size(); i++) {
            this.floorME.get(i).updateHitBox(this.floorME.get(i).getX() + x, this.floorME.get(i).getY());
        }
    }

    /** {@inheritDoc} */
    @Override
    public int getX() {
        return this.floorME.get(0).getX();
    }

    /** {@inheritDoc} */
    @Override
    public int getBIListWidth() {
        return this.floorME.get(0).getWidth();
    }

    /** {@inheritDoc} */
    @Override
    public int getBIListHeight() {
        return this.floorME.get(0).getHeight();
    }
}
