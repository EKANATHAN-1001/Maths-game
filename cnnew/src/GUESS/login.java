package GUESS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class login {
    private JPanel panel1;
    private JLabel l2;

    private JLabel l1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton button1;
    private JButton button2;
    static String uname;
    public static JFrame frame;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    public login()
    {
        frame = new JFrame("login frame");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700,500));
        frame.setResizable(false);
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              uname = textField1.getText();
             String pas = passwordField1.getText();
                try {
                    s = new Socket("127.0.0.1", 1234);
                    din = new DataInputStream(s.getInputStream());
                    dout= new DataOutputStream(s.getOutputStream());
                    dout.writeUTF(uname);
                    dout.writeUTF(pas);
                    dout.writeUTF("log");
                    System.out.println("wait");
                    String a = din.readUTF();
                    if(a.equals("OK")){
                        System.out.println("Login success");
                    JOptionPane.showMessageDialog(null,"Login successfull");

                        frame.setVisible(false);
                        //Game G = new Game();
                        Client1 G = new Client1();
                        G.frame.setVisible(true);}
                    else
                        JOptionPane.showMessageDialog(null,"Login failed");
                }
                catch (Exception e1)
                {
                    JOptionPane.showMessageDialog(null,"");
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Register R = new Register();
                R.frame.setVisible(true);
            }
        });
    }


}


