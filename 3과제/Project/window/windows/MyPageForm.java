package windows;

import java.awt.MenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import bases.BaseFrame;
import bases.BaseLabel;
import bases.BaseTable;
import jdbc.DbManager;
import model.UserModel;

public class MyPageForm extends BaseFrame {
	private BaseLabel jlPoint;
	private BaseTable jtbCenter;
	private Vector<Vector<String>> myData;
	private JPopupMenu jpmBack;
	private JPopupMenu jpmAfter;
	private JMenuItem jmiDelete;
	private JMenuItem jmiCancel;
	private JMenuItem jmiSeatChange;

	public MyPageForm() {
		// TODO Auto-generated constructor stub
		setFrame("마이페이지", 750, 450);
	}

	@Override
	public void make() {
		// TODO Auto-generated method stub

		jpmBack = new JPopupMenu();
		jmiDelete = new JMenuItem("삭제하기");

		jpmBack.add(jmiDelete);

		jpmAfter = new JPopupMenu();
		jmiCancel = new JMenuItem("예약 취소");
		jmiSeatChange = new JMenuItem("좌석 변경");

		jpmAfter.add(jmiCancel);
		jpmAfter.add(jmiSeatChange);

		myData = DbManager.db.getDb("SELECT \r\n" + "	r.r_no, n1.n_name, n2.n_name, s.date,\r\n"
				+ "    date_format(s.departTime, '%H:%i'),\r\n" + "    date_format(s.timeTaken, '%k:%i'),\r\n"
				+ "    r.income,\r\n" + "    r.expense\r\n" + "FROM 2023지방_3.reservation as r\r\n"
				+ "	join schedule as s\r\n" + "		on s.s_no = r.s_no\r\n" + "	join nation as n1\r\n"
				+ "		on n1.n_no = s.depart\r\n" + "	join nation as n2\r\n" + "		on n2.n_no = s.arrival\r\n"
				+ "where r.u_no = ?\r\n" + ";", UserModel.userData.get(0));

		jtbCenter = new BaseTable(myData, "번호", "출발지", "도착지", "날짜", "출발시간", "소요시간", "사용마일리지", "적립마일리지").setCenter();

		jlPoint = new BaseLabel("마일리지 : ");
	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpCenter.add(jtbCenter);

		jpBottom.setFlowRight();
		jpBottom.add(jlPoint);

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub

		JTable jtable = jtbCenter.jtable;
		jtable.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);

				int r = jtable.rowAtPoint(e.getPoint());
				jtable.setRowSelectionInterval(r, r);

				if (e.getButton() == 3) {
					jpmBack.show(jtable, e.getX(), e.getY());
				}

			}

		});
	}

}
