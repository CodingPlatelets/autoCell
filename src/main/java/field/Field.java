package field;

import cell.Cell;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private int width;
    private int height;
    private Cell[][] field;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        field = new Cell[height][width];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell place(int row, int col, Cell o) { //Cell o ϸ??????
        Cell ret = field[row][col];
        field[row][col] = o;
        return ret;
    }

    public Cell get(int row, int col) {
        return field[row][col];
    }

    public int getNeighbourInLive(int row, int col) {
        List<Cell> list = new ArrayList<Cell>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int r = row + i;
                int c = col + j;
                if (r > -1 && r < height && c > -1 && c < width && !(r == row && c == col)) {
                    list.add(field[r][c]);
                }
            }
        }
        int numOfLive = 0;
        for (Cell c : list) {
            if (c.isAlive()) {
                numOfLive++;
            }
        }
        return numOfLive;
    }

    public void clear() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                field[i][j].die();
            }
        }
    }
}
