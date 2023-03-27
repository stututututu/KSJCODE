package bases;

import java.awt.Dimension;

import javax.swing.JButton;

public class BaseButton extends JButton {
	public BaseButton(String text) {
		// TODO Auto-generated constructor stub
		super.setText(text);
		super.setPreferredSize(new Dimension(100, 25));
	}
}
