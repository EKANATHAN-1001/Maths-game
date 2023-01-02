package GUESS;

import javax.swing.*;
import java.awt.*;

public class GIfs {
    private JPanel panel1;
    private JPanel pav;
    private JLabel l1;
    JFrame frame;
    GIfs()
    {
        frame = new JFrame("Hurray ");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(250, 250));
        frame.setResizable(false);
        frame.add(panel1);
        frame.add(pav);
        frame.add(l1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GIfs();
    }
}
