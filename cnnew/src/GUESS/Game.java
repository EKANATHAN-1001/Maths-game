package GUESS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {
    private JPanel panel1;
    private Timer timer;
    private JPanel panel2;
    private JTextField textField1;
    private JButton CHECKButton;
    private JLabel l3;
    private JLabel lh;
    private JLabel ti;
    public static JFrame frame;
    int b=25;
    int h,second=30;
    public void simpleTimer()
    {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                second--;
                ti.setVisible(false);

                lh.setText(""+second);
                if(second<=0)
                {
                    JOptionPane.showMessageDialog(null,"Time Up");
                    second = 30;
                    int n = Integer.parseInt(l3.getText());
                    n--;
                    l3.setText(String.valueOf(n));

                }
                ti.setVisible(true);
            }
        });
    }

    public Game() {
        frame = new JFrame("login frame");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500,500));
        frame.setResizable(false);
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        simpleTimer();
        timer.start();
        CHECKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    int a = Integer.parseInt(textField1.getText());
                    if (a == b) {
                        JOptionPane.showMessageDialog(null, "Hurry ! you got right ");
                        l3.setText("10");
                        second=30;
                    } else {
                        int n = Integer.parseInt(l3.getText());
                        n--;
                        l3.setText(String.valueOf(n));
                        second=30;
                        if (n <= 0) {
                            JOptionPane.showMessageDialog(null, "Maximum trial reached ");
                            JOptionPane.showMessageDialog(null, "Guess a new number");
                            l3.setText("10");
                        }

                    }
            }
        });
    }
}
