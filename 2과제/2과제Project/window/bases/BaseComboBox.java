package bases;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JComboBox;

public class BaseComboBox extends JComboBox<String> {
	public BaseComboBox() {
		// TODO Auto-generated constructor stub
		super.setBackground(Color.white);
	}

	public BaseComboBox(Vector<Vector<String>> data) {
		// TODO Auto-generated constructor stub
		this();
		for (Vector<String> row : data) {
			super.addItem(row.get(0));
		}

	}

	public BaseComboBox(String... vals) {
		// TODO Auto-generated constructor stub
		this();
		for (String val : vals) {
			super.addItem(val);
		}
	}
}
