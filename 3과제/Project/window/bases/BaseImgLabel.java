package bases;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BaseImgLabel extends JLabel {
	public BaseImgLabel() {
		// TODO Auto-generated constructor stub

	}

	public BaseImgLabel(String text, ImageIcon icon, int width, int height) {
		// TODO Auto-generated constructor stub
		super.setText(text);

		Image img = icon.getImage();
		img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		super.setIcon(new ImageIcon(img));
	}

	public BaseImgLabel(String text, String url, int width, int height) {
		// TODO Auto-generated constructor stub
		super.setText(text);

		ImageIcon icon = new ImageIcon(url);
		Image img = icon.getImage();
		img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		super.setIcon(new ImageIcon(img));
	}

	public BaseImgLabel setTextFontSize(int size) {
		// TODO Auto-generated constructor stub
		super.setFont(new Font("HY헤드라인M", Font.PLAIN, size));
		return this;
	}

	public BaseImgLabel setTextSize(int size) {
		// TODO Auto-generated constructor stub
		super.setFont(new Font(null, Font.PLAIN, size));
		return this;
	}

	public BaseImgLabel setTextCenter() {
		// TODO Auto-generated method stub
		super.setHorizontalAlignment(JLabel.CENTER);
		return this;
	}

	public BaseImgLabel setTextRight() {
		// TODO Auto-generated method stub
		super.setHorizontalAlignment(JLabel.RIGHT);
		return this;
	}

	public BaseImgLabel setTextLeft() {
		// TODO Auto-generated method stub
		super.setHorizontalAlignment(JLabel.LEFT);
		return this;
	}

	public BaseImgLabel setTextLocateBottom() {
		// TODO Auto-generated method stub
		super.setHorizontalTextPosition(JLabel.CENTER);
		super.setVerticalTextPosition(JLabel.BOTTOM);
		return this;
	}

}
