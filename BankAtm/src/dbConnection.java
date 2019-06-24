import java.sql.*;
public class dbConnection{

 public Connection getConnection()
 {
     String url = "jdbc:mysql://localhost:3306/vishalbank";
     String username = "root";
     String password = "";
     Connection con = null;
     try 
     {
        Class.forName("com.mysql.jdbc.Driver");
     } 
     catch (ClassNotFoundException e1) 
     {
        // TODO Auto-generated catch block
        e1.printStackTrace();
    }

     try 
     {
         con = DriverManager.getConnection(url, username, password);
     } 
     catch (Exception e) 
     {
         // TODO Auto-generated catch block
         e.printStackTrace();
     }
    return con;
    }
}