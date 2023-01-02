package GUESS;
import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.sql.*;
public class server_login {
    String uname;

    public  static String UserNm;
    String password;
    static ServerSocket ss;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    server_login()
    {
        try {
            ss = new ServerSocket(1234);
            s=ss.accept();
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            uname = din.readUTF();
            password = din.readUTF();
            String ch = din.readUTF();
            if(ch.equals("Reg"))
            Cli_login();
            else if(ch.equals("log"))
            login();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"invalid port");
        }

    }

    public void login()
    {try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CN", "root", "");
        Statement st = con.createStatement();
        String sql = "select * from login where Uname ='" + uname + "'";
        ResultSet rs = st.executeQuery(sql);
        rs.first();
        String u = rs.getString(1);
        String p = rs.getString(2);
        System.out.println(u+""+p+" and "+uname+" and "+password);
        if(u.equals(uname)&&p.equals(password))
        {
             UserNm = uname;
           // UserNm = "manush";
            dout.writeUTF("OK");
        }
        else
            dout.writeUTF("NO");
    }catch (Exception e)
    {
        System.out.println("Connection Error");
    }}

    public void Cli_login()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CN","root","");
            Statement st = con.createStatement();
            String val ="1";
            String sql = "insert into login values('"+uname+"','"+password+"','"+val+"')";
            st.executeUpdate(sql);
            con.close();
            System.out.println("Success");

        }catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new server_login();
    }

}
