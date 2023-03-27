package windows;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;

import bases.BaseFrame;
import bases.BaseLabel;
import bases.BaseTable;
import jdbc.DbManager;
import messge.Msg;
import res.AirTicketManager;

public class AirChoseForm extends BaseFrame {
	private BaseLabel jlDate;
	private BaseTable jtbCenter;
	private JButton jbOkey;
	private Vector<String> a;

	public AirChoseForm() {
		// TODO Auto-generated constructor stub
		setFrame("항공권 선택", 600, 450);
	}

	public AirChoseForm(Vector<String> vector) {
		// TODO Auto-generated constructor stub\
		this.a = vector;
		setFrame("항공권 선택", 600, 450);
		
	}

	@Override
	public void make() {
		// TODO Auto-generated method stub

		SimpleDateFormat sdf = new SimpleDateFormat("MM.dd(E)");

		jlDate = new BaseLabel(sdf.format(AirTicketManager.date));

		System.out.println(AirTicketManager.month);
		System.out.println(AirTicketManager.day);
		System.out.println(AirTicketManager.depart);
		System.out.println(AirTicketManager.month);

		jtbCenter = new BaseTable(DbManager.db.getDb(
				"SELECT \r\n" + "	s.s_no, n1.n_name, n2.n_name,\r\n" + "	date_format(departTime, '%H:%i'),\r\n"
						+ "    date_format(addTime(s.departTime, s.timeTaken), '%H:%i'),\r\n"
						+ "    format(price, '#,###'),\r\n" + "    concat(50-count(r.s_no) , '/50')\r\n"
						+ "FROM 2023지방_3.schedule as s\r\n" + "	join nation as n1\r\n"
						+ "		on s.depart = n1.n_no\r\n" + "	join nation as n2\r\n"
						+ "		on s.arrival = n2.n_no\r\n" + "	join reservation as r\r\n"
						+ "		on s.s_no = r.s_no\r\n" + "	join boarding as b\r\n" + "		on r.r_no = b.r_no\r\n"
						+ "where month(date) = ? and day(date) = ?\r\n" + "	and depart = ? and arrival = ?\r\n"
						+ "group by r.s_no\r\n" + ";",
				AirTicketManager.month, AirTicketManager.day, AirTicketManager.depart, AirTicketManager.arrival), "번호",
				"출발지", "도착지", "출발시간", "도착시간", "가격", "잔여좌석");

		jbOkey = new JButton("확인");
	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpTop.add(jlDate.setLine(0, 0, 1, 0).setTextLeft().setTextSize(20));
		jpCenter.add(jtbCenter.setCenter());
		jpBottom.setFlowRight();
		jpBottom.add(jbOkey);

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub

		jbOkey.addActionListener(e -> {

			int row = jtbCenter.jtable.getSelectedRow();

			System.out.println(row);

			if (row == -1) {
				Msg.error("예약할 항공스케줄을 선택하세요.");
				return;
			}

			String s_no = jtbCenter.jtable.getValueAt(row, 0) + "";
			AirTicketManager.s_no = s_no;

			
			new BoardingChoseForm();

		});

	}

}
