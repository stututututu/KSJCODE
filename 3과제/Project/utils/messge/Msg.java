package messge;

import javax.swing.JOptionPane;

public class Msg {

	public static void error(String string) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, string, "경고", JOptionPane.ERROR_MESSAGE);
	}

	public static void info(String string) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, string, "정보", JOptionPane.INFORMATION_MESSAGE);
	}

	public static int qu(String string) {
		return JOptionPane.showConfirmDialog(null, string, "결제", JOptionPane.YES_NO_OPTION);
	}

}
