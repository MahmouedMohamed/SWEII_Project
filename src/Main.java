package date_base;
import javax.swing.*;
import java.lang.reflect.InvocationTargetException;



public class Main extends JFrame {

	public static void main(String[] args) throws Exception {
		DataAccess access=new DataAccess();
		access.ConnectDataBase();
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		SwingUtilities.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				gui mygui = null;
				try {
					mygui = new gui();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mygui.setVisible(true);
			}
		});
	}


}
