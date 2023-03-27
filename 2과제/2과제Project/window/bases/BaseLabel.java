package bases;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

public class BaseLabel extends JLabel {
	public BaseLabel() {
		// TODO Auto-generated constructor stub

	}

	public BaseLabel(String text) {
		// TODO Auto-generated constructor stub
		super.setText(text);
	}

	public BaseLabel(String text, int size) {
		// TODO Auto-generated constructor stub
		super.setText(text);
		super.setFont(new Font("HY헤드라인M", Font.PLAIN, size));
	}

	public BaseLabel setTextCenter() {
		// TODO Auto-generated constructor stub
		super.setHorizontalAlignment(JLabel.CENTER);
		return this;
	}

	public BaseLabel setTextLeft() {
		// TODO Auto-generated constructor stub
		super.setHorizontalAlignment(JLabel.LEFT);
		return this;
	}

	public BaseLabel setTextRight() {
		// TODO Auto-generated constructor stub
		super.setHorizontalAlignment(JLabel.RIGHT);
		return this;
	}

	public BaseLabel setLine() {
		// TODO Auto-generated method stub
		super.setBorder(BorderFactory.createLineBorder(Color.black));
		return this;
	}

	public BaseLabel setLine(int top, int left, int bottom, int right) {
		// TODO Auto-generated method stub
		super.setBorder(new MatteBorder(top, left, bottom, right, Color.black));
		return this;
	}

	public BaseLabel setTextFontSize(int size) {
		// TODO Auto-generated method stub
		super.setFont(new Font("HY헤드라인M", Font.PLAIN, size));
		return this;
	}

	public BaseLabel setTextSize(int size) {
		// TODO Auto-generated method stub
		super.setFont(new Font(null, Font.PLAIN, size));
		return this;
	}
}
