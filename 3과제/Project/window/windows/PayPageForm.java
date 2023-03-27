package windows;

import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bases.BaseFrame;
import bases.BaseLabel;
import bases.BasePanel;
import bases.BaseTable;
import jdbc.DbManager;
import messge.Msg;
import model.UserModel;
import res.AirTicketManager;

public class PayPageForm extends BaseFrame {
	private int resultPrice;
	private int priceAmount;
	private float havePoint;

	private BaseLabel jlPirceAmount;
	private BaseLabel jlHadPoint;
	private BaseLabel jlHavePoint;
	private BaseLabel jlUsingPoint;
	private JTextField jtfUsingPoint;
	private JButton jbPointUse;
	private BaseLabel jlPirce;
	private JButton jbPay;
	private BasePanel jpUsingPoing;
	private BasePanel jpHadPoint;
	private BasePanel jpHavePoint;
	private BasePanel jpPirceAmount;
	private Vector<String> priceData;
	private Vector<Vector> peopleData;
	private Vector<Vector<String>> tableData;
	private BaseTable jpCenterTable;
	private DecimalFormat df;
	private int usePoint;
	private int hadPoint;

	public PayPageForm() {
		// TODO Auto-generated constructor stub
		setFrame("결재", 679, 500);
	}

	public PayPageForm(Vector<Vector> peopleData) {
		// TODO Auto-generated constructor stub
		this.peopleData = peopleData;

		setFrame("결재", 679, 500);
	}

	@Override
	public void make() {
		// TODO Auto-generated method stub
		resultPrice = 0;
		priceAmount = 0;
		havePoint = 0;

		df = new DecimalFormat("#,###");

		priceData = DbManager.db
				.getDb("SELECT price,floor(price*0.8),floor(price*0.4)  FROM 2023지방_3.schedule where s_no = ?;",
						AirTicketManager.s_no)
				.get(0);

		tableData = new Vector<Vector<String>>();

		int base = Integer.parseInt(priceData.get(0));
		int small = Integer.parseInt(priceData.get(1));
		int toSmall = Integer.parseInt(priceData.get(2));

		for (int i = 0; i < peopleData.size(); i++) {
			String grade = peopleData.get(i).get(2) + "";

			Vector<String> newRow = new Vector<String>();
			newRow.add(peopleData.get(i).get(0) + "");
			newRow.add(grade);
			newRow.add(peopleData.get(i).get(3) + "");

			if (grade.equals("성인")) {
				priceAmount += base;
				newRow.add(df.format(base));
			}
			if (grade.equals("유아")) {
				priceAmount += small;
				newRow.add(df.format(small));
			}
			if (grade.equals("소아")) {
				priceAmount += toSmall;
				newRow.add(df.format(toSmall));
			}

			newRow.add(priceData.get(i) + "");

			tableData.add(newRow);
		}

		hadPoint = Integer.parseInt(UserModel.userData.get(6));

		havePoint = priceAmount * 0.01f;

		jlPirce = new BaseLabel("금액 : " + df.format(base) + "원");
		jlHadPoint = new BaseLabel("보유마일리즈 : " + df.format(hadPoint) + "원");
		jpHadPoint = new BasePanel().setFlowLeft();
		jpHadPoint.add(jlHadPoint);
		jlHavePoint = new BaseLabel("적립마일리즈 : " + df.format(havePoint) + "원");
		jpHavePoint = new BasePanel().setFlowLeft();
		jpHavePoint.add(jlHavePoint);
		jlUsingPoint = new BaseLabel("사용마일리즈 : ");
		jtfUsingPoint = new JTextField(12);
		jbPointUse = new JButton("사용");
		jpUsingPoing = new BasePanel().setFlowLeft();
		jpUsingPoing.add(jlUsingPoint);
		jpUsingPoing.add(jtfUsingPoint);
		jpUsingPoing.add(jbPointUse);

		jlPirceAmount = new BaseLabel("총 금액 : " + df.format(priceAmount) + "원");
		jbPay = new JButton("결제하기");

		jpPirceAmount = new BasePanel().setFlowRight();
		jpPirceAmount.add(jlPirceAmount);
		jpPirceAmount.add(jbPay);

		jpCenterTable = new BaseTable(tableData, "이름", "등급", "좌석", "가격");

		resultPrice = priceAmount;
	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpTop.addChild();
		jpTop.jpTop.add(new BaseLabel("결제하기").setTextCenter().setTextFontSize(25));

		jpTop.jpBottom.setFlowRight();
		jpTop.jpBottom.add(jlPirce);

		jpBottom.addChild();
		jpBottom.jpLeft.setGrid(3, 1, 0, 0);
		jpBottom.jpLeft.add(jpHadPoint);
		jpBottom.jpLeft.add(jpHavePoint);
		jpBottom.jpLeft.add(jpUsingPoing);

		jpBottom.jpRight.setGrid(3, 1, 0, 0);
		jpBottom.jpRight.add(jpPirceAmount);

		jpCenter.add(jpCenterTable);

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		jbPointUse.addActionListener(e -> {

			if (jbPointUse.getText().equals("사용")) {

				try { // 숫자가 아닌 경우 // 패턴 넣기 귀찬 . .
					usePoint = Integer.parseInt(jtfUsingPoint.getText());

				} catch (Exception e2) {
					// TODO: handle exception
					Msg.error("마일리지를 확인하세요.");
					usePoint = 0;
					jtfUsingPoint.setText("");
					jtfUsingPoint.requestFocus();
					return;

				}

				if (usePoint < 1 || hadPoint < usePoint || priceAmount < usePoint) {
					Msg.error("마일리지를 확인하세요.");
					usePoint = 0;
					jtfUsingPoint.setText("");
					jtfUsingPoint.requestFocus();
					return;
				}

				jlPirceAmount.setText("총 금액 : " + df.format(priceAmount - usePoint) + "원");
				resultPrice = priceAmount - usePoint;

				jtfUsingPoint.setEditable(false);

				havePoint = 0f;
				jlHavePoint.setText("적립마일리즈 : " + 0 + "원");

				jbPointUse.setText("초기화");

			} else {

				usePoint = 0;
				jlPirceAmount.setText("총 금액 : " + df.format(priceAmount) + "원");

				resultPrice = priceAmount;

				havePoint = priceAmount * 0.01f;
				jlHavePoint.setText("적립마일리즈 : " + df.format(havePoint) + "원");

				jtfUsingPoint.setText("");

				jtfUsingPoint.setEditable(true);
				jbPointUse.setText("사용");

			}

		});

		jbPay.addActionListener(e -> {
			int result = Msg.qu("총 : " + df.format(resultPrice) + "원을 결제하시겠습니까?");
			System.out.println(result);

			if (result == JOptionPane.YES_OPTION) {

				DbManager.db.setDb(
						"INSERT INTO `2023지방_3`.`reservation` (`u_no`, `s_no`, `income`, `expense`) VALUES (?, ?, ?, ?);",
						UserModel.userData.get(0), AirTicketManager.s_no, havePoint, usePoint);

				Vector<String> r_noIndex = DbManager.db
						.getDb("SELECT * FROM 2023지방_3.reservation where u_no = ? and s_no = ?;",
								UserModel.userData.get(0), AirTicketManager.s_no)
						.get(0);

				for (int i = 0; i < peopleData.size(); i++) {

					int agegroup = 0;

					System.out.println(peopleData.get(i));

					if (peopleData.get(i).get(2).equals("성인")) {
						agegroup = 1;
					}
					if (peopleData.get(i).get(2).equals("유아")) {
						agegroup = 2;
					}
					if (peopleData.get(i).get(2).equals("소아")) {
						agegroup = 3;
					}

					DbManager.db.setDb(
							"INSERT INTO `2023지방_3`.`boarding` (`r_no`, `agegroup`, `b_name`, `birth`, `seat`) VALUES (?, ?, ?, ?, ?);",
							r_noIndex.get(0), agegroup, peopleData.get(i).get(0), peopleData.get(i).get(1),
							peopleData.get(i).get(3));
				}

				DbManager.db.setDb("UPDATE `2023지방_3`.`user` SET `mileage` = mileage + (? - ?) WHERE (`u_no` = ?);",
						r_noIndex.get(3), r_noIndex.get(4), UserModel.userData.get(0));

				Msg.info("결제가 완료되었습니다.");
			}

			if (result == JOptionPane.NO_OPTION) {
				usePoint = 0;
				jlPirceAmount.setText("총 금액 : " + df.format(priceAmount) + "원");

				resultPrice = priceAmount;

				havePoint = priceAmount * 0.01f;
				jlHavePoint.setText("적립마일리즈 : " + df.format(havePoint) + "원");

				jtfUsingPoint.setText("");

				jtfUsingPoint.setEditable(true);
				jbPointUse.setText("사용");

			}

		});

	}

//	private void setPoint() {
//		// TODO Auto-generated method stub
//
//	}

}
