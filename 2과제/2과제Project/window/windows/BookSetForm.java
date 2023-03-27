package windows;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import bases.BaseComboBox;
import bases.BaseFrame;
import bases.BaseLabel;
import bases.BaseTable;
import jdbc.DbManager;
import messge.Msg;

public class BookSetForm extends BaseFrame {
	private BaseComboBox jcbSearchType;
	private JTextField jtfSearch;
	private JButton jbSearch;
	private BaseLabel jlCnt;
	private BaseTable jspCenter;
	private Vector<Vector<String>> bookData;
	private JPopupMenu jpmDelte;
	private JMenuItem jmiDelete;

	public BookSetForm() {
		// TODO Auto-generated constructor stub
		setFrame("도서관리", 774, 443);
	}

	@Override
	public void made() {
		// TODO Auto-generated method stub

		jpmDelte = new JPopupMenu();
		jmiDelete = new JMenuItem("삭제하기");

		jpmDelte.add(jmiDelete);

		jcbSearchType = new BaseComboBox("도서명", "저자");
		jtfSearch = new JTextField();
		jtfSearch.setPreferredSize(new Dimension(250, 27));

		jbSearch = new JButton("검색");

		jlCnt = new BaseLabel("검색권수 : n권");

	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpTop.addChild();
		jpTop.jpTop.add(new BaseLabel("도서 관리").setTextFontSize(30).setTextCenter());
		jpTop.jpBottom.setFlowRight();
		jpTop.jpBottom.add(jcbSearchType);
		jpTop.jpBottom.add(jtfSearch);
		jpTop.jpBottom.add(jbSearch);

		initGrid();

		jpBottom.setFlowRight();
		jpBottom.add(jlCnt);

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		jbSearch.addActionListener(e -> {
			initGrid();
		});

		jmiDelete.addActionListener(e -> {
			int row = jspCenter.jtb.getSelectedRow();
			System.out.println("row : " + row);

			String b_no = jspCenter.jtb.getValueAt(row, 6) + "";
			System.out.println("b_no : " + b_no);

			DbManager.db.setDb("DELETE FROM `2023지방_2`.`likebook` WHERE (`b_no` = ?);", b_no);
			DbManager.db.setDb("DELETE FROM `2023지방_2`.`rental` WHERE (`b_no` = ?);", b_no);
			DbManager.db.setDb("DELETE FROM `2023지방_2`.`book` WHERE (`b_no` = ?);", b_no);

			Msg.info("삭제가 완료되었습니다.");
			initGrid();
		});
	}

	private void initGrid() {
		// TODO Auto-generated method stub
		jpCenter.removeAll();

		String b_name = "";
		String b_author = "";

		if (jcbSearchType.getSelectedIndex() == 0) {
			b_name = jtfSearch.getText();
			b_name = b_name.trim();
			b_name.replace(" ", "");
		}
		if (jcbSearchType.getSelectedIndex() != 0) {
			b_author = jtfSearch.getText();
			b_author = b_name.trim();
			b_author.replace(" ", "");
		}

		bookData = DbManager.db.getDb(
				"SELECT b.b_img, b.b_name, d.d_name, b.b_author,b.b_count, b.b_page, b.b_no  FROM 2023지방_2.book as b\r\n"
						+ "	join division as d\r\n" + "		on b.d_no = d.d_no\r\n"
						+ "where replace(b.b_name, ' ', '') like concat('%',?,'%')\r\n"
						+ "	and	replace(b.b_author, ' ', '') like concat('%',?,'%')\r\n" + ";",
				b_name, b_author);

		if (bookData.size() == 0) {
			Msg.error("검색 결과가 없습니다.");
			jtfSearch.setText("");
			initGrid();
			return;
		}

		jspCenter = new BaseTable(bookData, "이미지", "도서명", "분류", "저자", "수량", "페이지", "");

		jpCenter.add(jspCenter.setWidth(100, 240, 100, 100, 100, 100, 0));
		jlCnt.setText("검색 건수 : " + bookData.size() + "권");

		jspCenter.jtb.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);

				if (e.getButton() == 3) {

					int r = jspCenter.jtb.rowAtPoint(e.getPoint());
					jspCenter.jtb.setRowSelectionInterval(r, r);

					jpmDelte.show(jspCenter.jtb, e.getX(), e.getY());
				}

			}

		});

		super.refresh();

	}
}
