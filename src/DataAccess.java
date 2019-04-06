package date_base;

import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DataAccess implements DataAcc {
    protected String UserNmae;
    protected double Password;
    protected String TableName;
    protected Question q;

    protected static void ConnectDataBase() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample_db?autoReconnect=true&useSSL=false", "root", "12345");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

   /* public Question GETquestion(String title)
    {

    }*/

    public void Setquestion(Question q)
    {

    }
}
