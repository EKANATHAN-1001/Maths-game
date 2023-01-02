package GUESS;
import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.stream.IntStream;

public class Server1 {
    String UserNm;
    int a;

    String lvl="";
    String qn[] = {"How much is 90 – 19?","Find the value of x; if x = (2 × 3) + 11.","\n" +
            "How much is 190 – 87 + 16?","\n" +
            "Arrange the numbers in ascending order: 36, 12, 29, 21, 7.","In 24,673 ; the place-value of 6 is ….. .","\n" +
            "The difference between smallest 4 digits and  largest 3 digits is ……. .","\n" +
            "A number is divisible by 5 if its unit digit is ………","\n" +
            "Find the sum of 111 + 222 + 333","\n" +
            "Subtract 457 from 832","\n" +
            "Solve : 200 – (96 ÷ 4)","\n"+"19 + ……. = 42","\n"+"What is the greatest two digit number?","\n"+
    "The largest number of six digits is ….. .","\n"+
    "The sum of the least number of three digits and largest number of two digits is ….. .","\n"+
            "1010 gram = ……… kg.","\n"+"Average of three person’s age is 9 years. Find the sum of there age.",
            "\n"+"How many months are equal to 45 days?","\n"+
    "How many diagonals are there in a quadrilateral?","\n"+"If one side of a square is 35 m, then the area is …….",
            "\n"+"How many surfaces are there in a cube?","\n"+"How many parts are there in a triangle?","\n"+
    "Absolute value of -20 or |-20| is ….. .","\n"+"What is –10 – (–6) equals to?","\n"+"Find the sum; –1.54 + 5.093."
    ,"\n"+"What is the value of a^0?","\n"+"What is three fifth of 100?","\n"+"What is the remainder of 21 divided by 7?"
    ,"\n"+"How many years are there in a decade?","\n"+"What is the value of x if x2 = 169","\n"+"In a century how many months are there?",
"\n"+"How many sides are there in a nonagon?","\n"+"How many months have 30 day","\n"+"In words number 14 can be written as ……."
           ,"\n"+"13 – 13 × 111 – 111 = ……..","\n"+"6 – (5 – 3) + 10 = ……..","\n"+"Solve: x - 3 = 5","\n"+"A car can cover a distance of 522 km on 36 liters of petrol. How far can it travel on 14 liters of petrol?"
,"\n"+"A clock seen through a mirror, shows quarter past three. What is the correct time shown by the clock?","\n"+"A clock seen through a mirror shows 8 o’clock. What is the correct time?"
,"\n"+"If the day tomorrow is Sunday, what was it yesterday?","\n"+"78 ÷ 5 ÷ 0.5 = ?","\n"+"If ∆ means multiply the first number by second, then (3 ∆ 5) ∆ 2 = ?","\n"+"If the number X 78 Y is divisible by 55 then value of X and Y are:"
,"\n"+"SiWhat temperature at Celsius scale is equal to 300°K."+"\n"+"Each edge of a cube is increased by 50%. What will be the percent increase in its volume?","\n"+"The number which is neither prime nor composite is ……. ."
,"\n"+"The diagonals of a rhombus are 30 cm and 40 cm long. Find its side.","\n"+"Fill in the blanks; 4, 6, 12, 14, 28, 30, (?)","\n"+"(9321 + 5406 + 1001) ÷ (498 + 929 + 660) = ……… ."};
    String ans[]={"71","17","119","7, 12, 21, 29, 36 ","600","1","5","666","375","176","23","99","71","999999","199","1.01","27",
            "1.5","2","1225","6","3","20","-4","3.553","1","60","3","10","13","1200","9","11","fourteen","-1541","14",
            "8","203","8.45","4","Friday","31.2","30","4, 5","27","237.5","1","25","60","7.5"};

    Server1() {
        try {
            System.out.println("The no of qn "+qn.length+" The no of ans "+ans.length);
            ServerSocket ss = new ServerSocket(7444);
            Socket s = ss.accept();
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            DataInputStream din = new DataInputStream(s.getInputStream());
            System.out.println("Wait for qn");
            String lvle;
            try{

                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CN", "root", "");
                Statement st = con.createStatement();
                UserNm=din.readUTF();
                System.out.println("The user name "+UserNm);
                String sql = "select * from login where uname ='"+UserNm+"'";
                ResultSet rs = st.executeQuery(sql);
                rs.first();
                lvle = rs.getString(3);
                System.out.println("Ack ");
                sql = "select * from login";
                rs = st.executeQuery(sql);
                rs.first();
                String ek = "Name \t\t Level \n";
                do {
                    ek =ek+rs.getString(1)+"\t\t"+rs.getString(3)+"\n";
                }while (rs.next());
                dout.writeUTF(lvle);
                dout.writeUTF(ek);

            }catch (Exception er)
            {
                System.out.println("Error");
            }
            do {
                lvl = din.readUTF();
                System.out.println("ksdhfsn " + lvl);
                if (!lvl.equals("EXIT")) {
                    switch (Integer.parseInt(lvl.trim())) {
                        case 1:
                            System.out.println("hai");
                            a = (int) (Math.random() * (10 - 1) + 1);
                            System.out.println("ksdj" + a);
                            System.out.println("The qn " + qn[a] + "The ans " + ans[a]);
                            dout.writeUTF(qn[a]);
                            System.out.println("Send Qn");
                            dout.writeUTF(ans[a]);
                            break;
                        case 2:
                            System.out.println("hai 2");
                            a = (int) (Math.random() * (20 - 11) + 11);
                            System.out.println(a);
                            System.out.println("The qn " + qn[a] + "The ans " + ans[a]);
                            dout.writeUTF(qn[a]);
                            dout.writeUTF(ans[a]);
                            break;
                        case 3:
                            System.out.println("hai 2");
                            a = (int) (Math.random() * (30 - 21) + 21);
                            System.out.println(a);
                            System.out.println("The qn " + qn[a] + "The ans " + ans[a]);
                            dout.writeUTF(qn[a]);
                            dout.writeUTF(ans[a]);
                            break;
                        case 4:
                            System.out.println("hai 2");
                            a = (int) (Math.random() * (40 - 31) + 31);
                            System.out.println(a);
                            System.out.println("The qn " + qn[a] + "The ans " + ans[a]);
                            dout.writeUTF(qn[a]);
                            dout.writeUTF(ans[a]);
                            break;
                        case 5:
                            System.out.println("hai 2");
                            a = (int) (Math.random() * (50 - 41) + 41);
                            System.out.println(a);
                            System.out.println("The qn " + qn[a] + "The ans " + ans[a]);
                            dout.writeUTF(qn[a]);
                            dout.writeUTF(ans[a]);
                            break;
                    }
                    System.out.println("End of switch");
                }
            }while (!lvl.equals("EXIT")) ;

            String dblvl =din.readUTF();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CN", "root", "");
                Statement st = con.createStatement();
                System.out.println("Execute update lvl query");
                String sql = "update  login set level ='"+dblvl+"' where Uname = '"+UserNm+"'";
                st.executeUpdate(sql);
                con.close();
            }catch (Exception e1)
            {
                System.out.println("Error");
            }



        }
        catch (Exception e)
        {
            System.out.println("Error in socket");
        }
    }

    public static void main(String[] args) {
        new Server1();
    }
}
