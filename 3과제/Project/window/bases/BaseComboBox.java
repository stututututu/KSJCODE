package bases;

import java.util.Vector;

import javax.swing.JComboBox;

public class BaseComboBox extends JComboBox<String> {
	public BaseComboBox() {
		// TODO Auto-generated constructor stub
	}

	public BaseComboBox(String... vals) {
		// TODO Auto-generated constructor stub
		for (String val : vals) {
			super.addItem(val);
		}
	}

	public BaseComboBox(Vector<Vector<String>> data) {
		// TODO Auto-generated constructor stub
		for (Vector<String> row : data) {
			super.addItem(row.get(0));
		}
	}

	public BaseComboBox(String first, Vector<Vector<String>> data) {
		// TODO Auto-generated constructor stub
		super.addItem(first);
		for (Vector<String> row : data) {
			super.addItem(row.get(0));
		}
	}

	public BaseComboBox refreshData(Vector<Vector<String>> data) {
		// TODO Auto-generated constructor stub
		super.removeAll();
		for (Vector<String> row : data) {
			super.addItem(row.get(0));
		}
		return this;
	}

}
