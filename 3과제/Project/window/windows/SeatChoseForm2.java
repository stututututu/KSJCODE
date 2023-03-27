package windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JScrollPane;

import bases.BaseFrame;
import bases.BaseLabel;
import bases.BasePanel;
import bases.BaseScrollPane;
import jdbc.DbManager;
import messge.Msg;
import res.AirTicketManager;

public class SeatChoseForm2 extends BaseFrame {

	private int selectNameRow = -1;

	private BaseScrollPane jspCenter;
	private BasePanel jpASeat;
	private BasePanel jpSeats;
	private BaseLabel jlPeopleCnt;
	private JButton jbDone;
	private Vector<BaseLabel> jlASeatData;
	private Vector<BaseLabel> jlBSeatData;
	private Vector<BaseLabel> jlCSeatData;
	private Vector<BaseLabel> jlDSeatData;
	private Vector<BaseLabel> jlESeatData;
	private BasePanel jpBSeat;
	private BasePanel jpCSeat;
	private BasePanel jpDSeat;
	private BasePanel jpESeat;
	private Vector<Vector> peopleData;
	private Vector<BaseLabel> boardingLabelData;

	private Vector<BasePanel> boardingPanelData;

	private Vector<Vector<String>> chosedSeatData;

	private HashMap<Integer, BaseLabel> selectSeat;

	private Vector<BaseLabel> seatData;

	public SeatChoseForm2(Vector<Vector> data) {
		// TODO Auto-generated constructor stub
		this.peopleData = data;
		setFrame("좌석선택", 726, 512);
	}

	@Override
	public void make() {
		// TODO Auto-generated method stub

		chosedSeatData = DbManager.db.getDb(
				"SELECT \r\n" + "	seat\r\n" + "FROM 2023지방_3.schedule as s\r\n" + "	join nation as n1\r\n"
						+ "		on s.depart = n1.n_no\r\n" + "	join nation as n2\r\n"
						+ "		on s.arrival = n2.n_no\r\n" + "	join reservation as r\r\n"
						+ "		on s.s_no = r.s_no\r\n" + "	join boarding as b\r\n" + "		on r.r_no = b.r_no\r\n"
						+ "where month(date) = ? and day(date) = ?\r\n" + "	and depart = ? and arrival = ?\r\n" + ";",
				AirTicketManager.month, AirTicketManager.day, AirTicketManager.depart, AirTicketManager.arrival);

		int vjpgap = 3;
		jpASeat = new BasePanel().setGrid(10, 1, 0, vjpgap);
		jpBSeat = new BasePanel().setGrid(10, 1, 0, vjpgap);
		jpCSeat = new BasePanel().setGrid(10, 1, 0, vjpgap);
		jpDSeat = new BasePanel().setGrid(10, 1, 0, vjpgap);
		jpESeat = new BasePanel().setGrid(10, 1, 0, vjpgap);

		jlASeatData = new Vector<BaseLabel>();
		jlBSeatData = new Vector<BaseLabel>();
		jlCSeatData = new Vector<BaseLabel>();
		jlDSeatData = new Vector<BaseLabel>();
		jlESeatData = new Vector<BaseLabel>();

		int lbSize = 60;
		selectSeat = new HashMap<>();

		seatData = new Vector<BaseLabel>();

		for (int i = 0; i < 10; i++) {
			jlASeatData.add(new BaseLabel("A" + String.format("%02d", i + 1)).setLine().setPSize(lbSize, lbSize));
			jlBSeatData.add(new BaseLabel("B" + String.format("%02d", i + 1)).setLine().setPSize(lbSize, lbSize));
			jlCSeatData.add(new BaseLabel("C" + String.format("%02d", i + 1)).setLine().setPSize(lbSize, lbSize));
			jlDSeatData.add(new BaseLabel("D" + String.format("%02d", i + 1)).setLine().setPSize(lbSize, lbSize));
			jlESeatData.add(new BaseLabel("E" + String.format("%02d", i + 1)).setLine().setPSize(lbSize, lbSize));

			jpASeat.add(jlASeatData.get(i));
			jpBSeat.add(jlBSeatData.get(i));
			jpCSeat.add(jlCSeatData.get(i));
			jpDSeat.add(jlDSeatData.get(i));
			jpESeat.add(jlESeatData.get(i));

		}

		seatData.addAll(jlASeatData);
		seatData.addAll(jlBSeatData);
		seatData.addAll(jlCSeatData);
		seatData.addAll(jlDSeatData);
		seatData.addAll(jlESeatData);

		for (BaseLabel baseLabel : seatData) {

			for (Vector<String> row : chosedSeatData) {
				if (baseLabel.getText().equals(row.get(0))) {
					baseLabel.setBackground(Color.gray);
				}

			}

			baseLabel.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);

					if (baseLabel.getBackground() != Color.white) {
						Msg.error("선택된 좌석은 선택이 불가능 합니다.");
						return;
					}

					if (selectNameRow == -1) {
						Msg.error("승객을 선택해주세요.");
						return;
					}

					if (selectSeat.containsKey(selectNameRow)) {
						System.out.println(selectSeat.get(selectNameRow).getText());
						selectSeat.get(selectNameRow).setBackgroundCol(Color.WHITE);
					}

					selectSeat.put(selectNameRow, baseLabel);
					baseLabel.setBackgroundRed();
					boardingLabelData.get(selectNameRow).setText(baseLabel.getText());

				}

			});

		}

		jpSeats = new BasePanel();
		jspCenter = new BaseScrollPane(jpSeats);

		jlPeopleCnt = new BaseLabel("총 n명");
		jbDone = new JButton("선택완료");
	}

	@Override
	public void design() {
		// TODO Auto-generated method stub

		jpSeats.addChild();
		jpSeats.jpTop.setGrid(1, 5, 0, 0);
		jpSeats.jpTop.add(new BaseLabel("A").setTextCenter());
		jpSeats.jpTop.add(new BaseLabel("B").setTextCenter());
		jpSeats.jpTop.add(new BaseLabel("C").setTextCenter());
		jpSeats.jpTop.add(new BaseLabel("D").setTextCenter());
		jpSeats.jpTop.add(new BaseLabel("E").setTextCenter());

		jpSeats.jpLeft.setGrid(10, 1, 0, 50);
		jpSeats.jpRight.setGrid(10, 1, 0, 50);

		for (int i = 1; i <= 10; i++) {
			jpSeats.jpLeft.add(new BaseLabel(i + "").setTextCenter());
			jpSeats.jpRight.add(new BaseLabel(i + "").setTextCenter());
		}

		jpSeats.jpCenter.addChild();
		int jphgap = 3;
		jpSeats.jpCenter.jpLeft.setGrid(1, 2, jphgap, 0);
		jpSeats.jpCenter.jpRight.setGrid(1, 2, jphgap, 0);

		jpSeats.jpCenter.jpLeft.add(jpASeat);
		jpSeats.jpCenter.jpLeft.add(jpBSeat);
		jpSeats.jpCenter.jpCenter.add(jpCSeat);
		jpSeats.jpCenter.jpRight.add(jpDSeat);
		jpSeats.jpCenter.jpRight.add(jpESeat);

		int hemtygap = 50;
		jpSeats.jpCenter.jpCenter.setEmtyBorder(0, hemtygap, 0, hemtygap);
		jpSeats.jpCenter.setEmtyBorder(0, 10, 0, 10);

		jpLeft.add(jspCenter.setPSize(475, 0));

		jpCenter.addChild();
		jpCenter.jpTop.add(jlPeopleCnt.setTextLeft().setTextSize(20));

		jpCenter.jpCenter.setGrid(10, 1, 0, 5);

		boardingPanelData = new Vector<BasePanel>();
		boardingLabelData = new Vector<BaseLabel>();
		int i = 0;

		for (Vector row : peopleData) {

			boardingPanelData.add(new BasePanel());

			boardingPanelData.get(i).setFlowLeft();
			boardingPanelData.get(i).add(new BaseLabel(row.get(0) + " -"));
			boardingLabelData.add(new BaseLabel());

			boardingPanelData.get(i).add(boardingLabelData.get(i));
			boardingPanelData.get(i).setLine();

			int ii = i;
			boardingPanelData.get(i).addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);

					for (BasePanel basePanel : boardingPanelData) {
						basePanel.setLine();
					}

					boardingPanelData.get(ii).setLine(Color.blue);

					selectNameRow = ii;

					System.out.println("selectNameRow : " + selectNameRow);
				}

			});

			jpCenter.jpCenter.add(boardingPanelData.get(i));

			i++;

		}

		jpCenter.jpBottom.add(jbDone);
	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		jbDone.addActionListener(e -> {
			for (int i = 0; i < peopleData.size(); i++) {

				if (boardingLabelData.get(i).getText().isBlank()) {
					Msg.error("승객 좌석을 모두 선택하세요.");
					return;
				}

			}
			
//			[bases.BaseLabel[,60,6,21x16,alignmentX=0.0,alignmentY=0.0,border=,flags=8388608,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,horizontalAlignment=CENTER,horizontalTextPosition=TRAILING,iconTextGap=4,labelFor=,text=E01,verticalAlignment=CENTER,verticalTextPosition=CENTER], bases.BaseLabel[,73,6,22x16,alignmentX=0.0,alignmentY=0.0,border=,flags=8388608,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,horizontalAlignment=CENTER,horizontalTextPosition=TRAILING,iconTextGap=4,labelFor=,text=C02,verticalAlignment=CENTER,verticalTextPosition=CENTER]]
//					[[sadf aa, 1999-01-01, 성인], [rrowo adf, 2020-01-01, 소아]]
							
			
			
			for (int i = 0; i < peopleData.size(); i++) {
				peopleData.get(i).add(boardingLabelData.get(i).getText());
			}
								
			
			new PayPageForm(peopleData);
			
		});
	}

}
