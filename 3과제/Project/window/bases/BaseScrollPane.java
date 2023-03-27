package bases;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;

public class BaseScrollPane extends JScrollPane {
	public BaseScrollPane() {
		// TODO Auto-generated constructor stub
		super.setBackground(Color.white);
		super.getViewport().setBackground(Color.white);
	}

	public BaseScrollPane(BasePanel jpSeats) {
		// TODO Auto-generated constructor stub
		super.setBackground(Color.white);
		super.getViewport().setBackground(Color.white);

		super.setViewportView(jpSeats);
	}

	public BaseScrollPane setPSize(int width, int height) {
		// TODO Auto-generated method stub
		super.setPreferredSize(new Dimension(width, height));
		return this;
	}

}
