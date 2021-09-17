package cellmachine;

import cell.Cell;
import field.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CellMachineTest {

    static Field field;

    @BeforeAll
    static void beforeAll() {
        field = new Field(3, 3);
        for (int row = 0; row < field.getHeight(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                field.place(row, col, new Cell());
            }
        }
    }

    public void neighbor(int[][] dieOrLive) {
        for (int row = 0; row < field.getHeight(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                Cell cell = field.get(row, col);
                int numOfLive = field.getNeighbourInLive(row, col);
                if (cell.isAlive()) {
                    if (numOfLive < 2 || numOfLive > 3) {
                        dieOrLive[row][col] = -1;
                    }
                } else if (numOfLive == 3) {
                    dieOrLive[row][col] = 1;
                }
            }
        }
    }

    public void report(int[][] dieOrLive) {
        for (int i = 0; i < field.getHeight(); i++) {
            for (int j = 0; j < field.getWidth(); j++) {
                System.out.print("[" + i + "][" + j + "]");
                System.out.print(":  -->");
                if (dieOrLive[i][j] == 1) {
                    field.get(i, j).reborn();
                    System.out.println("reborn");
                } else if (dieOrLive[i][j] == -1) {
                    field.get(i, j).die();
                    System.out.println("die");
                } else {
                    System.out.println("remaining");
                }
            }
        }
    }

    @DisplayName("corner")
    @Test
    void dis1() {
        field.get(0, 0).reborn();
        int[][] dieOrLive = new int[field.getHeight()][field.getWidth()];
        neighbor(dieOrLive);
        report(dieOrLive);
        var result = new int[][]{
                {-1, 0, 0}, {0, 0, 0}, {0, 0, 0}
        };
        assertArrayEquals(result, dieOrLive);
    }

    @DisplayName("diagonal")
    @Test
    void dis2() {
        field.get(0, 0).reborn();
        field.get(1, 1).reborn();
        field.get(2, 2).reborn();
        int[][] dieOrLive = new int[field.getHeight()][field.getWidth()];
        neighbor(dieOrLive);
        report(dieOrLive);
        var result = new int[][]{
                {-1, 0, 0}, {0, 0, 0}, {0, 0, -1}
        };
        assertArrayEquals(result, dieOrLive);
    }

    @DisplayName("Dead")
    @Test
    void dis3() {
        field.get(0, 0).reborn();
        field.get(0, 1).reborn();
        field.get(0, 2).reborn();
        field.get(1, 1).reborn();
        field.get(2, 1).reborn();
        int[][] dieOrLive = new int[field.getHeight()][field.getWidth()];
        neighbor(dieOrLive);
        report(dieOrLive);
        var result = new int[][]{
                {0, 0, 0}, {0, -1, 0}, {0, -1, 0}
        };
        assertArrayEquals(result, dieOrLive);
    }

    @DisplayName("reborn")
    @Test
    void dis4() {
        field.get(0, 0).reborn();
        field.get(0, 1).reborn();
        field.get(0, 2).reborn();
        field.get(1, 1).reborn();
        int[][] dieOrLive = new int[field.getHeight()][field.getWidth()];
        neighbor(dieOrLive);
        var result = new int[][]{
                {0, 0, 0}, {1, 0, 1}, {0, 0, 0}
        };
        assertArrayEquals(result, dieOrLive);
    }

    @DisplayName("still")
    @Test
    void dis5() {
        field.get(0, 0).reborn();
        field.get(0, 1).reborn();
        field.get(1, 0).reborn();
        field.get(1, 1).reborn();
        int[][] dieOrLive = new int[field.getHeight()][field.getWidth()];
        neighbor(dieOrLive);
        report(dieOrLive);
        var result = new int[][]{
                {0, 0, 0}, {0, 0, 0}, {0, 0, 0}
        };
        assertArrayEquals(result, dieOrLive);
    }


    @BeforeEach
    public void before() {
        System.out.println("before");
        field.clear();
    }

}