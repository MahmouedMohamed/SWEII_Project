package date_base;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class gui extends JFrame{
    private JButton showQuizButton;
    private JPanel rootpanel;
    private JButton enterANewQuizButton;

    public  gui () throws InvocationTargetException, InterruptedException {
        add(rootpanel);
        setTitle("mostafa");
        setSize(400,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        showQuizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                third_gui thirdd=new third_gui();
                thirdd.setVisible(true);            }

        }
        );
        enterANewQuizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                second_gui second = null;
                try {
                    second = new second_gui();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                second.setVisible(true);
            }

        }
        );

    }
}
