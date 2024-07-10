import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
public class Utility {
    public static  Connection connect()throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdata","root","vaidiknamdev");
        return con;
    }
    
}
