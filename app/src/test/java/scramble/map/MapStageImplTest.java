package scramble.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import scramble.model.map.impl.MapStageImpl;

class MapStageImplTest {

    @Test
    void testConstructorInitializesEmptyList() {
        final MapStageImpl<String> mapStage = new MapStageImpl<>();
        assertEquals(0, mapStage.size());
    }

    @Test
    void testSizeReturnsCorrectNumberOfColumns() {
        final MapStageImpl<String> mapStage = new MapStageImpl<>();

        mapStage.addColumn("Column 1");
        mapStage.addColumn("Column 2");

        assertEquals(2, mapStage.size());
    }

    @Test
    void testGetColumnReturnsCorrectColumn() {
        final MapStageImpl<Integer> mapStage = new MapStageImpl<>();

        mapStage.addColumn(0);
        mapStage.addColumn(1);
        mapStage.addColumn(2);

        assertEquals(0, mapStage.getColumn(0));
        assertEquals(1, mapStage.getColumn(1));
        assertEquals(2, mapStage.getColumn(2));
    }

}
