package field;

import cell.Cell;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    /**
     * If a serializable class does not explicitly declare a serialVersionUID, then the serialization runtime will calculate a default serialVersionUID value for that class based on various aspects of the class, as described in the Java(TM) Object Serialization Specification. However, it is strongly recommended that all serializable classes explicitly declare serialVersionUID values, since the default serialVersionUID computation is highly sensitive to class details that may vary depending on compiler implementations, and can thus result in unexpected InvalidClassExceptions during deserialization. Therefore, to guarantee a consistent serialVersionUID value across different java compiler implementations, a serializable class must declare an explicit serialVersionUID value. It is also strongly advised that explicit serialVersionUID declarations use the private modifier where possible, since such declarations apply only to the immediately declaring class — serialVersionUID fields are not useful as inherited members.
     */
    private static final long serialVersionUID = -5258995676212660595L;
    private static final int GRID_SIZE = 20;
    private Field thisField;

    public View(Field field) {
        thisField = field;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int row = 0; row < thisField.getHeight(); row++) {
            for (int col = 0; col < thisField.getWidth(); col++) {
                Cell cell = thisField.get(row, col);
                if (cell != null) {
                    cell.draw(g, col * GRID_SIZE, row * GRID_SIZE, GRID_SIZE);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(thisField.getWidth() * GRID_SIZE + 1, thisField.getHeight() * GRID_SIZE + 1);
    }

    public static void main(String[] args) {
        Field field = new Field(60, 40);
        for (int row = 0; row < field.getHeight(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                field.place(row, col, new Cell());
            }
        }
        View view = new View(field);
        JFrame frame = new JFrame();
//        JButton right = new JButton("right");
//        frame.add(right, BorderLayout.EAST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Cells");
        frame.add(view);
        frame.pack();
        frame.setVisible(true);
    }

}
