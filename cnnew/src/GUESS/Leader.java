package GUESS;

import javax.swing.*;
import java.awt.*;

public class Leader {
    public JTextArea ta1;
    private JPanel panel1;
    public JFrame frame;
    public Leader()
    {
        frame = new JFrame("Game ");
        frame.setPreferredSize(new Dimension(750, 750));
        frame.setResizable(false);
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
