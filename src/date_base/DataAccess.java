package date_base;

import com.mysql.jdbc.Statement;
import java.util.Scanner;
import java.sql.*;

public class DataAccess {
    protected Connection con;
    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false", "root", "12345");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected static void connectDataBase()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false", "root", "12345");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void getQuiz(String title,int idAdmin,int idUser) throws Exception {
        Scanner in = new Scanner(System.in);
        String choose;
        int sum = 0;
        connectDataBase();
        Statement stmt = (Statement) con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM `" + idAdmin + "`.`" + title + "`");

        while (rs.next()) {
            String quest = rs.getString("question");
            String choice_A = rs.getString("choiceA");
            String choice_B = rs.getString("choiceB");
            String choice_C = rs.getString("choiceC");
            System.out.println(quest + "    " + choice_A + "    " + choice_B + "   " + choice_C);
            System.out.println("enter your choice :");
            choose = in.nextLine();
            String correct_answer = rs.getString("correctAnswer");
            if (correct_answer.equals(choose)) {
                sum++;
            }

        }
        System.out.print("the submtion of all correct answers is : ");
        System.out.println(sum);
        try {
            stmt.executeUpdate("INSERT INTO `" + idAdmin + "`.`users` (`userID`, `quizTitile`, `userScore`) VALUES ('" + idUser + "', '" + title + "', '" + sum + "')");
        } catch (SQLException e)
        {

            stmt.executeUpdate("UPDATE `" + idAdmin + "`.`users` SET `userScore` = '"+sum+"' WHERE (`userID` = '"+idUser+"')");
        }
    }

    protected void addQuestion(String title, int idAdmin)
    {

        while (true) {
            Question q = new Question();
            connectDataBase();//check if we delete it does it change anything
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the question");
            String new_question = in.nextLine();
            q.setQuestion(new_question);
            System.out.println("Enter the firstChoice");
            String firstChoice = in.nextLine();
            System.out.println("Enter the secondChoice");
            String secondChoice = in.nextLine();
            System.out.println("Enter the thirdChoice");
            String thirdChoice = in.nextLine();
            System.out.println("Enter the correctAnswer");
            String correctAnswer = in.nextLine();
            q.SetCorrectAnswer(correctAnswer);
            try {
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO `"+idAdmin+"`.`"+title+"`(`question`,`choiceA`,`choiceB`,`choiceC`,`correctAnswer`)VALUES(?,?,?,?,?)");

                pstmt.setString(1, q.getQuestion());
                pstmt.setString(2, firstChoice);
                pstmt.setString(3, secondChoice);
                pstmt.setString(4, thirdChoice);
                pstmt.setString(5, q.GetCorrectAnswer());
                pstmt.execute();
                System.out.println("Insert Succeeded");

            } catch (SQLException e1) {

                System.out.println("Could not insert data to the database " + e1.getMessage());
            }
            System.out.println("Do u want another question   0 for Nay 1 for Yay");
            int ch=in.nextInt();
            if (ch==0)break;
        }
    }
    protected void deleteQuiz (String title , int idAdmin)
    {

        try {
            Statement pstmt = (Statement) con.createStatement();//
            pstmt.executeUpdate("DROP TABLE `"+idAdmin+"`.`"+title+"`");
            pstmt.executeUpdate("DELETE FROM `"+idAdmin+"`.`quizzes` WHERE (`type` = '"+title+"')");
            System.out.println("Delete Succeeded");
        } catch (SQLException e) {
            System.out.println("Could not Delete this table from the database ");
        }
    }
    protected void renameQuiz (String title ,String newTitle, int idAdmin)
    {

        try {
            Statement pstmt = (Statement) con.createStatement();//
            pstmt.executeUpdate("ALTER TABLE `"+idAdmin+"`.`"+title+"`\n"+
                    "RENAME TO`"+idAdmin+"`.`"+newTitle+"`");
            pstmt.executeUpdate("UPDATE `"+idAdmin+"`.`quizzes` SET `type` = '"+newTitle+"' WHERE (`type` = '"+title+"')");
            System.out.println("Rename Succeeded");
        } catch (SQLException e) {
            System.out.println("Could not Rename this table from the database ");
        }
    }
    protected void deleteQuestion (String title,int idAdmin,String deletedQuestion)
    {
        Statement pstmt = null;
        try {
            pstmt = (Statement) con.createStatement();
            pstmt.executeUpdate("DELETE FROM `"+idAdmin+"`.`"+title+"` WHERE (`question` = '"+deletedQuestion+"')");
            System.out.println("Deleting question Succeeded");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    protected void createAdminSchema (int id) throws SQLException
    {
        connectDataBase();
        Statement stmt = (Statement) con.createStatement();
        stmt.executeUpdate("CREATE SCHEMA `"+id+"`");//CREATE SCHEMA `new_schema` ;
    }





}

