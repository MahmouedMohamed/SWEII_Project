package date_base;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class create_quiz extends Quiz {
    protected Connection con;

    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false", "root", "12345");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Statement stmt;

    {
        try {
            stmt = (Statement) con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createQuiz(int idAdmin, String title,int passScore,int finalScore,Durat durat) throws Exception {
        DataAccess database = new DataAccess();
        database.connectDataBase();





        quizTitle=title;
        quizPassScore=passScore;
        quizFinalScore=finalScore;
        quizObjct.SetSec(durat.GetSec());
        quizObjct.SetMin(durat.GetMin());
        quizObjct.SetH(durat.GetH());




        try
        {
            database.createAdminSchema(idAdmin);
            createQuizzesNameTable(idAdmin);
            stmt.executeUpdate("INSERT INTO `" + idAdmin + "`.`quizzes` (`type`, `passScore`,`finalScore`, `duration`) VALUES ('"+quizTitle+"', '"+quizPassScore+"', '"+quizFinalScore+"', '"+quizObjct.GetH()+":"+quizObjct.GetMin()+":"+quizObjct.GetSec()+"')");
            createUserTable(idAdmin);
            createQuizTable(idAdmin, quizTitle);
            System.out.println("Successfully created " + quizTitle + " table");
            database.addQuestion(quizTitle, idAdmin);
        }
        //this part if the admin has been in the database already before and he is just wanna to create a quiz
        catch (SQLException e)
        {
            try {
                createQuizTable(idAdmin, quizTitle);
                stmt.executeUpdate("INSERT INTO `" + idAdmin + "`.`quizzes` (`type`, `passScore`,`finalScore`, `duration`) VALUES ('"+quizTitle+"', '"+quizPassScore+"', '"+quizFinalScore+"', '"+quizObjct.GetH()+":"+quizObjct.GetMin()+":"+quizObjct.GetSec()+"')");
                System.out.println("Successfully created " + quizTitle + " table");
                database.addQuestion(quizTitle, idAdmin);
            }catch (SQLException e1)
            {
                System.out.println("there is a quiz its name is "+quizTitle);
                database.addQuestion(quizTitle, idAdmin);
            }

        }



    }

    protected void createQuizzesNameTable(int idAdmin) throws SQLException {
        stmt.executeUpdate("CREATE TABLE `" + idAdmin + "`.`quizzes` (\n" +
                "`type` VARCHAR(45) NOT NULL,\n" +
                "`passScore` INT NOT NULL,\n" +
                "`finalScore` INT NOT NULL,\n"+
                "`duration` TIME NOT NULL,\n" +
                "PRIMARY KEY (`type`));");
    }

    protected void createUserTable(int idAdmin) throws SQLException {
        stmt.executeUpdate("CREATE TABLE `" + idAdmin + "`.`users` (\n" +
                "  `userID` INT NOT NULL,\n" +
                "  `quizTitile` VARCHAR(45) NOT NULL,\n" +
                "  `userScore` INT NOT NULL,\n" +
                "  PRIMARY KEY (`userID`));");
    }

    protected void createQuizTable(int idAdmin, String title) throws SQLException {

        stmt.executeUpdate("CREATE TABLE `" + idAdmin + "`.`" + title + "` (\n" +
                "  `question` VARCHAR(100) NOT NULL,\n" +
                "  `choiceA` VARCHAR(45) NOT NULL,\n" +
                "  `choiceB` VARCHAR(45) NOT NULL,\n" +
                "  `choiceC` VARCHAR(45) NOT NULL,\n" +
                "  `correctAnswer` VARCHAR(45) NOT NULL,\n" +
                "  UNIQUE INDEX `question_UNIQUE` (`question` ASC))");

    }


}
