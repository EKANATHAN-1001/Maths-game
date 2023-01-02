package GUESS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client1 {
    private JTextField anstext;
    private Timer timer;

    int h,second=30;
    private JPanel panel1;
    private JLabel lvl;
    private JLabel qn1;
    private JButton QnButton;
    private JButton Ansbutton;
    private JButton Exitbutton;
    private JSlider XP;
    private JLabel ti;
    private JLabel lh;
    private JLabel l3;
    private JPanel pn1;
    private JButton leaderBoardButton;
    public static JFrame frame;

    private Socket s;
    private DataOutputStream dout;
    private DataInputStream din;
    private String qn;
    private String ans,ek;
    private int count = 1;

    public void simpleTimer()
    {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                second--;
                lh.setText(""+second);
                if(second<=0)
                {
                    JOptionPane.showMessageDialog(null,"Time Up !");
                    second = 30;
                    int n = Integer.parseInt(l3.getText().trim());
                    n--;
                    JOptionPane.showMessageDialog(null,"The remaining no of Turns "+n);
                    l3.setText(String.valueOf(n)+" ");

                }
            }
        });
    }
    public Client1()
    {

            frame = new JFrame("Game ");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(1000, 750));
            frame.setResizable(false);
            frame.add(panel1);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            try{
         s = new Socket("127.0.0.1",7444);
         dout = new DataOutputStream(s.getOutputStream());
         din = new DataInputStream(s.getInputStream());
        login n  = new login();
         n.frame.setVisible(false);
         System.out.println(login.uname);
        // dout.writeUTF(login.uname);
                dout.writeUTF("Manush");
         lvl.setText(din.readUTF()+" ");
         ek = din.readUTF();
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null,"Connection error ");
            }

        QnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                String g = lvl.getText();
                dout.writeUTF(g);
                qn = din.readUTF();
                ans = din.readUTF();
                qn1.setText(qn);
                    simpleTimer();
                    timer.start();
                }
                catch (Exception e1)
                {
                    JOptionPane.showMessageDialog(null,"Invalid connection");
                }
            }
        });
        Ansbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ans1 = anstext.getText().trim();
                System.out.println("The string " +new String(ans));
                if(ans.equals(ans1))
                {
                    JOptionPane.showMessageDialog(null,"Hurray ! You gave Correct answer");
                    count++;
                    XP.setValue(count);
                    second = 30;
                    try {
                        dout.writeUTF(lvl.getText());
                        qn = din.readUTF();
                        ans = din.readUTF();
                        qn1.setText(qn);
                        anstext.setText("");
                    }catch (Exception w)
                    {
                        JOptionPane.showMessageDialog(null,"Send Failed");
                    }
                    if(count >= 10)
                    {

                        int gs = Integer.parseInt(lvl.getText().trim());
                        gs++;
                        System.out.println("level up");
                        lvl.setText(String.valueOf(gs)+" ");
                        if(gs>10)
                        {
                            JOptionPane.showMessageDialog(null,"You're the Legend of this GAME");
                            try {
                                dout.writeUTF("EXIT");
                                Client1.frame.setVisible(false);
                            }catch (Exception j)
                            {
                                JOptionPane.showMessageDialog(null,"Error");
                            }
                        }
                        JOptionPane.showMessageDialog(null,"Level UP , Now Qn is Hard");
                        count=0;
                        XP.setValue(1);
                        System.out.println("Slide 0");

                    }
                    try {
                        String g = lvl.getText();
                        dout.writeUTF(g);
                        qn = din.readUTF();
                        ans = din.readUTF();
                        qn1.setText(qn);
                    }
                    catch (Exception e1)
                    {
                        JOptionPane.showMessageDialog(null,"Invalid connection");
                    }

                }
                else {
                    JOptionPane.showMessageDialog(null,"Wrong answer , Try again");
                    int n = Integer.parseInt(l3.getText().trim());
                    n--;
                    JOptionPane.showMessageDialog(null,"The remaining no of Turns "+n);
                    if (n <= 0) {
                        JOptionPane.showMessageDialog(null, "Maximum trial reached ");
                        JOptionPane.showMessageDialog(null, "Here is the new Question");
                        try {
                            dout.writeUTF(lvl.getText());
                            qn = din.readUTF();
                            ans = din.readUTF();
                            qn1.setText(qn);
                            anstext.setText("");
                        }catch (Exception w)
                        {
                            JOptionPane.showMessageDialog(null,"Send Failed");
                        }
                        n=10;
                    }
                   l3.setText(String.valueOf(n)+" ");
                    second =30;
                }
            }
        });
        Exitbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dout.writeUTF("EXIT");
                    dout.writeUTF(lvl.getText().trim());
                    Client1.frame.setVisible(false);
                }
                catch (Exception e1)
                {
                    JOptionPane.showMessageDialog(null,"Error");
                }
            }
        });
        leaderBoardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Leader gg =new Leader();
                gg.ta1.setText(ek);
            }
        });
    }

    public static void main(String[] args) {
        new Client1();

    }
}
