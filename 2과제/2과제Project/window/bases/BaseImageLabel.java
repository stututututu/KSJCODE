package bases;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BaseImageLabel extends JLabel {
	public BaseImageLabel() {
		// TODO Auto-generated constructor stub
	}

	public BaseImageLabel(String txt, String url, int width, int height) {
		// TODO Auto-generated constructor stub
		super.setText(txt);

		ImageIcon icon = new ImageIcon(url);
		Image img = icon.getImage();
		img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);

		super.setIcon(icon);
	}

	public BaseImageLabel(String txt, ImageIcon icon, int width, int height) {
		// TODO Auto-generated constructor stub
		super.setText(txt);
		Image img = icon.getImage();
		img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);

		super.setIcon(icon);
	}

	public BaseImageLabel(String txt, String url, int width, int height, int size) {
		// TODO Auto-generated constructor stub
		super.setText(txt);

		ImageIcon icon = new ImageIcon(url);
		Image img = icon.getImage();
		img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);

		super.setFont(new Font("HY헤드라인M", Font.PLAIN, size));

		super.setIcon(icon);
	}

	public BaseImageLabel(String txt, ImageIcon icon, int width, int height, int size) {
		// TODO Auto-generated constructor stub
		super.setText(txt);
		Image img = icon.getImage();
		img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);

		super.setFont(new Font("HY헤드라인M", Font.PLAIN, size));

		super.setIcon(icon);
	}

	public BaseImageLabel setTextCenter() {
		// TODO Auto-generated constructor stub
		super.setHorizontalAlignment(JLabel.CENTER);
		return this;
	}

	public BaseImageLabel setLocateCenter() {
		// TODO Auto-generated constructor stub
		super.setHorizontalTextPosition(JLabel.CENTER);
		super.setVerticalTextPosition(JLabel.CENTER);
		return this;
	}

	public BaseImageLabel setLocateBottom() {
		// TODO Auto-generated constructor stub
		super.setHorizontalTextPosition(JLabel.CENTER);
		super.setVerticalTextPosition(JLabel.BOTTOM);
		return this;
	}

	public BaseImageLabel setTextColor(Color color) {
		// TODO Auto-generated constructor stub
		super.setForeground(color);
		return this;
	}

}
