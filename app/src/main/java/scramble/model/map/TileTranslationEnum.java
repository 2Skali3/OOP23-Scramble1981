package scramble.model.map;

/** Enum for the tile translations. */
public enum TileTranslationEnum {
    /** Tile 0 enum. */
    TILE_0(0, 46),
    /** Tile 1 enum. */
    TILE_1(1, 44),
    /** Tile 2A enum. */
    TILE_2A(2, 1),
    /** Tile 2B enum. */
    TILE_2B(2, 47),
    /** Tile 3A enum. */
    TILE_3A(3, 2),
    /** Tile 3B enum. */
    TILE_3B(3, 50),
    /** Tile 4A enum. */
    TILE_4A(4, 4),
    /** Tile 4B enum. */
    TILE_4B(4, 45),
    /** Tile 5 enum. */
    TILE_5(5, 48),
    /** Tile 6 enum. */
    TILE_6(6, 51),
    /** Tile 7 enum. */
    TILE_7(7, 49),
    /** Tile 8 enum. */
    TILE_8(8, 54),
    /** Tile 9 enum. */
    TILE_9(9, 52),
    /** Tile 11 enum. */
    TILE_11(11, 53),
    /** Tile 13 enum. */
    TILE_13(13, 58),
    /** Tile 14 enum. */
    TILE_14(14, 16),
    /** Tile 24 enum. */
    TILE_24(24, 94),
    /** Tile 25 enum. */
    TILE_25(25, 92),
    /** Tile 27 enum. */
    TILE_27(27, 93),
    /** Tile 28 enum. */
    TILE_28(28, 98),
    /** Tile 29 enum. */
    TILE_29(29, 96),
    /** Tile 30 enum. */
    TILE_30(30, 99),
    /** Tile 31 enum. */
    TILE_31(31, 97),
    /** Tile 33 enum. */
    TILE_33(33, 209),
    /** Tile 35 enum. */
    TILE_35(35, 27),
    /** Tile 36 enum. */
    TILE_36(36, 31),
    /** Tile 37 enum. */
    TILE_37(37, 30),
    /** Tile 38 enum. */
    TILE_38(38, 17),
    /** Tile 39 enum. */
    TILE_39(39, 29),
    /** Tile 40 enum. */
    TILE_40(40, 25);

    private final int index;
    private final int decodedValue;

    TileTranslationEnum(final int index, final int decodedValue) {
        this.index = index;
        this.decodedValue = decodedValue;
    }

    /**
     * Getter for index.
     * 
     * @return tile index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Getter for value.
     *
     * @return tile value
     */
    public int getDecodedValue() {
        return decodedValue;
    }

}
