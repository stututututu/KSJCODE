package windows;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;

import bases.BaseFrame;
import bases.BaseLabel;
import jdbc.DbManager;
import messge.Msg;
import res.AirTicketManager;

public class DateChoseForm extends BaseFrame {
	private AirSearchForm airSearchForm;
	private BaseLabel jlDown;
	private BaseLabel jlUp;
	private BaseLabel jlDate;
	private Calendar cal;
	private int nowDay;
	private int year;
	private int month;
	private int nowMonth;
	private int nowYear;
	private String startIndex;
	private String lastIndex;

	public DateChoseForm(AirSearchForm airSearchForm, String startIndex, String lastIndex) {
		// TODO Auto-generated constructor stub

		this.startIndex = startIndex;
		this.lastIndex = lastIndex;

		this.airSearchForm = airSearchForm;
		cal = Calendar.getInstance();

		nowMonth = cal.get(Calendar.MONTH);
		nowDay = cal.get(Calendar.DAY_OF_MONTH);
		nowYear = cal.get(Calendar.YEAR);

		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);

		cal.set(year, month, 1);

		setFrame("날짜선택", 500, 500);
	}

	@Override
	public void make() {
		// TODO Auto-generated method stub
		jlDown = new BaseLabel("◀");
		jlDate = new BaseLabel(year + "년 " + String.format("%02d", month + 1) + "월");
		jlUp = new BaseLabel("▶");

		jlDown.setEnabled(false);

		jpCenter.addChild();
		jpCenter.jpTop.setGrid(1, 7, 0, 0);
		jpCenter.jpTop.add(new BaseLabel("일"));
		jpCenter.jpTop.add(new BaseLabel("월"));
		jpCenter.jpTop.add(new BaseLabel("화"));
		jpCenter.jpTop.add(new BaseLabel("수"));
		jpCenter.jpTop.add(new BaseLabel("목"));
		jpCenter.jpTop.add(new BaseLabel("금"));
		jpCenter.jpTop.add(new BaseLabel("토"));

		jpCenter.jpCenter.setGrid(6, 7, 0, 0);

	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpTop.setFlowCenter();
		jpTop.add(jlDown);
		jpTop.add(jlDate);
		jpTop.add(jlUp);

		initGrid();
	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		jlUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);

				up();
				jlDate.setText(year + "년 " + String.format("%02d", month + 1) + "월");

			}
		});

		jlDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);

				down();
				jlDate.setText(year + "년 " + String.format("%02d", month + 1) + "월");

			}
		});
	}

	private void up() {
		// TODO Auto-generated method stub
		cal.add(Calendar.MONTH, +1);

		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);

		initGrid();

		if (!(nowYear == year && month <= nowMonth)) {
			jlDown.setEnabled(true);
			return;
		}
	}

	private void down() {
		// TODO Auto-generated method stub

		if (nowYear == year && month <= nowMonth) {
			return;
		}

		cal.add(Calendar.MONTH, -1);

		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);

		initGrid();

		if (nowYear == year && month <= nowMonth) {
			jlDown.setEnabled(false);
		}
	}

	private void initGrid() {
		// TODO Auto-generated method stub

		int startDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
		int dayCnt = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int tmpDay = 42 - dayCnt - startDay;

		System.out.println("startDay : " + startDay);
		System.out.println("dayCnt : " + dayCnt);
		System.out.println("tmpDay : " + tmpDay);

		cal.add(Calendar.MONTH, -1);

		int beforeDateMax = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println("beforeDateMax : " + beforeDateMax);

		cal.add(Calendar.MONTH, +1);

		jpCenter.jpCenter.removeAll();

		for (int i = beforeDateMax - (startDay - 1); i <= beforeDateMax; i++) {
			jpCenter.jpCenter.add(new BaseLabel(i + "").setLine().setEnabled());
		}

		Vector<BaseLabel> jls = new Vector<BaseLabel>();
		for (int i = 1; i <= dayCnt; i++) {

			int ii = i;

			String text = i + "";

			Vector<Vector<String>> sData = DbManager.db.getDb("SELECT * FROM 2023지방_3.schedule\r\n" + "\r\n"
					+ "	where month(date) = ? and day(date) = ? \r\n" + "		and depart = ? and arrival = ?;",
					month + 1, i, startIndex, lastIndex);

			if (sData.size() != 0) {
				text = "<html>" + text + "<br>" + "(" + sData.size() + ")" + "</html>";
			}

			BaseLabel jlTmp = new BaseLabel(text).setTextCenter().setLine();
			jls.add(jlTmp);

			jpCenter.jpCenter.add(jls.get(i - 1));

			jls.get(i - 1).addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);

					if (sData.size() == 0) {
						Msg.error("해당날짜에는 항공스케줄이 없습니다.");
						return;
					}

					int day = ii;
//					int day = Integer.parseInt(jls.get(i).getText());
					System.out.println("day : " + day);

					String choseDate = year + "-" + String.format("%02d", month + 1) + "-" + String.format("%02d", day);

					System.out.println("선택 날짜 : " + choseDate);

					for (BaseLabel jl : jls) {
						jl.setLine(Color.black);
					}

					if (!jls.get(ii - 1).getText().contains("(")) {
						Msg.error("해당날짜에는 항공스케줄이 없습니다.");
						return;
					}

					jls.get(ii - 1).setLine(Color.blue);

					if (e.getClickCount() == 2) {
						airSearchForm.jtfDate.setText(choseDate);
						AirTicketManager.month = month + 1 + "";
						AirTicketManager.day = day + "";

						AirTicketManager.date = new Date(2023, month, day);

						dispose();
					}

				}

			});

		}

//		for (int i = 0; i < jls.size(); i++) {
//
//			int ii = i;
//
//			jpCenter.jpCenter.add(jls.get(i));
//
//			jls.get(i).addMouseListener(new MouseAdapter() {
//				@Override
//				public void mousePressed(MouseEvent e) {
//					// TODO Auto-generated method stub
//					super.mousePressed(e);
//
//					int day = Integer.parseInt(jls.get(ii).getText());
//					System.out.println("day : " + day);
//
//					String choseDate = year + "-" + String.format("%02d", month + 1) + "-" + String.format("%02d", day);
//
//					System.out.println("선택 날짜 : " + choseDate);
//
//					jls.get(ii).setLine(Color.blue);
//
//					if (e.getClickCount() == 2) {
//						airSearchForm.jtfDate.setText(choseDate);
//						dispose();
//					}
//
//				}
//
//			});
//
//		}
//		for (int i = 1; i <= dayCnt; i++) {
//
//			jpCenter.jpCenter.add(jlTmp);
//
//			jlTmp.addMouseListener(new MouseAdapter() {
//				@Override
//				public void mousePressed(MouseEvent e) {
//					// TODO Auto-generated method stub
//					super.mousePressed(e);
//
//					int day = Integer.parseInt(jlTmp.getText());
//					System.out.println("day : " + day);
//
//					String choseDate = year + "-" + String.format("%02d", month + 1) + "-" + String.format("%02d", day);
//
//					System.out.println("선택 날짜 : " + choseDate);
//
//					jlTmp.setLine(Color.blue);
//
//					if (e.getClickCount() == 2) {
//						airSearchForm.jtfDate.setText(choseDate);
//						dispose();
//					}
//
//				}
//
//			});
//
//		}

		for (int i = 1; i <= tmpDay; i++) {
			jpCenter.jpCenter.add(new BaseLabel(i + "").setLine().setEnabled());
		}

	}

}
