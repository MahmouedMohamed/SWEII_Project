package date_base;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class second_gui extends JFrame implements DataAcc {
    private JButton SAVEButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JLabel choiceC;
    private JPanel panel2;
    private JTextField textField5;
    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sample_db?autoReconnect=true&useSSL=false", "root", "12345");;
    private static void ConnectDataBase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample_db?autoReconnect=true&useSSL=false", "root", "12345");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  second_gui () throws SQLException {

        add(panel2);
        setTitle("DARWISH");
        setSize(400,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        SAVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    ConnectDataBase();
                    PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO sample_table (question,choiceA,choiceB,choiceC)VALUES(?,?,?,?)");
                    preparedStatement.setString(1, textField1.getText());
                    preparedStatement.setString(2, textField2.getText());
                    preparedStatement.setString(3, textField3.getText());
                    preparedStatement.setString(4, textField4.getText());
                    preparedStatement.execute();
                    System.out.println("Insert Succeeded");
                } catch (SQLException e1) {

                    System.out.println("Could not insert data to the database " + e1.getMessage());
                }


            }

        }
        );

    }
}
