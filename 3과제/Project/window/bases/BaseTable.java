package bases;

import java.awt.Color;
import java.awt.Component;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class BaseTable extends JScrollPane {
	public Vector<Vector<String>> data;
//	public Vector<Vector<Object>> data;
	public Vector<String> cols;
	public DefaultTableModel dtm;
	public JTable jtable;

//	public BaseTable(Vector<Vector<Object>> data, String... cols) {
	public BaseTable(Vector<Vector<String>> data, String... cols) {
		// TODO Auto-generated constructor stub
		this.data = data;
		createCols(cols);

		Vector<Boolean> b = new Vector<Boolean>();
		for (int i = 0; i < data.size(); i++) {
			b.add(new Boolean(false));
		}

		dtm = new DefaultTableModel(data, this.cols);
		dtm.addColumn("추가 컬럼", b);

		jtable = new JTable(dtm) {

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				System.out.println("isCellEditable");
				if (column == 0) {
					return true;

				}
				return false;
//				return super.isCellEditable(row, column);
			}

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// TODO Auto-generated method stub
				System.out.println("prepareRenderer");
				Component c = super.prepareRenderer(renderer, row, column);
//				c.setForeground(Color.red);

				return c;

			}

			@Override
			public Class<?> getColumnClass(int column) {
				// TODO Auto-generated method stub
				System.out.println("getColumnClass");
				if (column == 0) {
					return Boolean.class;
				}

				return super.getColumnClass(column);

			}

//			
//			@Override
//			public Component prepareEditor(TableCellEditor editor, int row, int column) {
//				// TODO Auto-generated method stub
//				return super.prepareEditor(editor, row, column);
//			}
		};

		jtable.moveColumn(4, 0);
		System.out.println("열 옮겨짐");
		initTable();
		super.setViewportView(jtable);

	}

	private void createCols(String[] cols) {
		// TODO Auto-generated method stub
		this.cols = new Vector<String>();
		for (String col : cols) {
			this.cols.add(col);
		}
	}

	private void initTable() {
		// TODO Auto-generated method stub
		super.setBackground(Color.white);
		super.getViewport().setBackground(Color.white);
		jtable.getTableHeader().setBackground(Color.white);

		jtable.getTableHeader().setReorderingAllowed(false);
		jtable.getTableHeader().setResizingAllowed(false);

	}

	public BaseTable setCenter() {
		// TODO Auto-generated method stub
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

		jtable.setDefaultRenderer(Object.class, dtcr);

		return this;
	}

	public BaseTable addData(String... vals) {
		// TODO Auto-generated method stub

		Vector<String> row = new Vector<String>();
		for (String val : vals) {
			row.add(val);
		}

//		dtm.getDataVector().addAll(data);

		dtm.addRow(row);

		return this;
	}

//	public BaseTable ChangeCol(boolean is, int col, ) {
//		
//		
//		return this;
//
//	}
}
