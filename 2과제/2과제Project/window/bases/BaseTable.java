package bases;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

public class BaseTable extends JScrollPane {
	public DefaultTableModel dtm;
	public JTable jtb;

	public BaseTable(Vector<Vector<String>> data, String... colVals) {
		// TODO Auto-generated constructor stub
		Vector<String> cols = new Vector<String>();
		for (String col : colVals) {
			cols.add(col);
		}

		dtm = new DefaultTableModel(data, cols);
		jtb = new JTable(dtm);
		setCenter();
		initTable();
		super.setViewportView(jtb);
	}

	private void initTable() {
		// TODO Auto-generated method stub
		super.setBackground(Color.white);
		super.getViewport().setBackground(Color.white);
		jtb.getTableHeader().setBackground(Color.white);
		jtb.setBackground(Color.white);

		jtb.getTableHeader().setReorderingAllowed(false);
		jtb.getTableHeader().setResizingAllowed(false);

	}

	public BaseTable setWidth(int... sizes) {
		// TODO Auto-generated method stub

		for (int j = 0; j < sizes.length; j++) {
			jtb.getColumnModel().getColumn(j).setMaxWidth(sizes[j]);
			jtb.getColumnModel().getColumn(j).setMinWidth(sizes[j]);
			jtb.getColumnModel().getColumn(j).setWidth(sizes[j]);

		}

		return this;

	}

	public void setCenter() {
		// TODO Auto-generated method stub
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

		jtb.setDefaultRenderer(Object.class, dtcr);

	}

}
