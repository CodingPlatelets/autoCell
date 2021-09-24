package cellmachine;

import cell.Cell;
import field.Field;
import field.View;

import javax.swing.*;

public class CellMachine {
    static Field field = new Field(30, 30);

    public static void main(String[] args) {

        for (int row = 0; row < field.getHeight(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                field.place(row, col, new Cell());
            }
        }
        for (int row = 0; row < field.getHeight(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                Cell cell = field.get(row, col);
                if (Math.random() < 0.2) {
                    cell.reborn();
                }
            }
        }
        // 保存数组
        int[][] dieOrLive = new int[field.getHeight()][field.getWidth()];

        //窗口
        View view = new View(field);
        JFrame frame = getFrame(view);

        for (int i = 0; i < 1000; i++) {
            neighbor(dieOrLive);
            report(dieOrLive);
            frame.repaint();
            System.out.println("UPDATE");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void neighbor(int[][] dieOrLive) {
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

    public static void report(int[][] dieOrLive) {
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

    public static JFrame getFrame(View v) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Cells");
        frame.add(v);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }

}
