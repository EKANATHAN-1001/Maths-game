package GUESS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Register {
    private JLabel l1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton REGISTERButton;
    private JButton CANCELButton;
    private JButton CLEARButton;
    private JPanel panel1;
    private JPanel panel2;
    public static JFrame frame;
    static  Socket s;
    static DataInputStream din;
    static DataOutputStream dout;

    public Register() {

        frame = new JFrame("login frame");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500,500));
        frame.setResizable(false);
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        CLEARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                passwordField1.setText("");
                passwordField2.setText("");
            }
        });
        REGISTERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String p1 = passwordField1.getText();
                String p2 = passwordField2.getText();
                String u = textField1.getText();
                if(p1.equals(p2)) {
                    try {
                        s = new Socket("127.0.0.1", 1234);
                        din = new DataInputStream(s.getInputStream());
                        dout= new DataOutputStream(s.getOutputStream());
                        dout.writeUTF(u);
                        dout.writeUTF(p2);
                        dout.writeUTF("Reg");
                    }
                    catch (Exception e1)
                    {
                        JOptionPane.showMessageDialog(null,"");
                    }
                    JOptionPane.showMessageDialog(null, "Register Successfull");
                    frame.setVisible(false);
                    login a = new login();
                    a.frame.setVisible(true);
                }else
                    JOptionPane.showMessageDialog(null,"Give a valid password");

            }
        });
        CANCELButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                login a = new login();
                a.frame.setVisible(true);
            }
        });
    }
}
