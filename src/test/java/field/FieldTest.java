package field;

import cell.Cell;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {
    static Field field;

    @BeforeAll
    static void beforeAll() {
        field = new Field(4, 4);
        for (int row = 0; row < field.getHeight(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                field.place(row, col, new Cell());
            }
        }
    }

    @Test
    void getWidth() {
        assertEquals(4, field.getWidth());
    }

    @Test
    void getHeight() {
        assertEquals(4, field.getHeight());
    }


    @DisplayName("corner")
    @Test
    void getNeighbourInLive1() {
        field.get(0, 0).reborn();
        field.get(0, 1).reborn();
        assertEquals(1, field.getNeighbourInLive(0, 0));
    }

    @DisplayName("diagonal")
    @Test
    void getNeighbourInLive2() {
        field.get(0, 0).reborn();
        field.get(1, 1).reborn();
        assertEquals(1, field.getNeighbourInLive(0, 0));
    }

    @DisplayName("center")
    @Test
    void getNeighbourInLive3() {
        field.get(0, 0).reborn();
        field.get(1, 1).reborn();
        field.get(2, 2).reborn();

        assertEquals(2, field.getNeighbourInLive(1, 1));
        assertEquals(1, field.getNeighbourInLive(2, 2));
        assertEquals(1, field.getNeighbourInLive(3, 3));
        assertEquals(0, field.getNeighbourInLive(3, 0));

    }


    @BeforeEach
    public void before() {
        field.clear();
    }
}