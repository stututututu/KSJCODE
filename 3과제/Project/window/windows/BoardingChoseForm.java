package windows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JTextField;

import bases.BaseFrame;
import bases.BaseLabel;
import bases.BasePanel;
import bases.BaseTable;
import messge.Msg;
import res.AirTicketManager;

public class BoardingChoseForm extends BaseFrame {
	private BaseLabel jlDate;
	private JButton jbInsert;
	private JTextField jtfFristName;
	private JTextField jtfLastName;
	private JTextField jtfBirth;
	private BaseTable jtbCenter;
	private JButton jbDelete;
	private JButton jbDone;
	private BasePanel jpName;

	public BoardingChoseForm() {
		// TODO Auto-generated constructor stub
		setFrame("탑승객정보입력", 928, 311);
	}

	@Override
	public void make() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yy.MM.dd(E) " + AirTicketManager.departString + " → " + AirTicketManager.arrivalString);
		jlDate = new BaseLabel(sdf.format(AirTicketManager.date)).setTextFontSize(20).setTextLeft();
		jbInsert = new JButton("입력");

		jtfFristName = new JTextField(8);
		jtfLastName = new JTextField(8);

		jpName = new BasePanel().setGrid(1, 2, 5, 0);
		jpName.add(jtfFristName);
		jpName.add(jtfLastName);

		jtfBirth = new JTextField(16);

		Vector<Vector<String>> data = new Vector<Vector<String>>();

		jtbCenter = new BaseTable(data, "이름(영문)", "생년월일", "등급");

		jbDelete = new JButton("삭제하기");
		jbDone = new JButton("완료");

	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpTop.addChild();
		jpTop.jpCenter.add(jlDate);
		jpTop.jpRight.add(jbInsert);

		jpCenter.addChild();
		jpCenter.jpLeft.addChild();

		jpCenter.jpLeft.jpLeft.setGrid(4, 1, 0, 15);
		jpCenter.jpLeft.jpLeft.add(new BaseLabel("이름(영문)"));
		jpCenter.jpLeft.jpLeft.add(new BaseLabel("생년월일"));
		jpCenter.jpLeft.jpLeft.add(new BaseLabel(""));
		jpCenter.jpLeft.jpLeft.add(new BaseLabel(""));

		jpCenter.jpLeft.jpCenter.setGrid(4, 1, 0, 15);
		jpCenter.jpLeft.jpCenter.add(jpName);
		jpCenter.jpLeft.jpCenter.add(jtfBirth);
		jpCenter.jpLeft.jpCenter.add(new BaseLabel());
		jpCenter.jpLeft.jpCenter.add(new BaseLabel());

		jpCenter.jpCenter.add(jtbCenter.setCenter());

		jpBottom.setFlowRight();
		jpBottom.add(jbDelete);
		jpBottom.add(jbDone);

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		jbInsert.addActionListener(e -> {

			String firstName = jtfFristName.getText();
			String lastName = jtfLastName.getText();
			String birth = jtfBirth.getText();

			System.err.println("firstName : " + firstName);
			System.err.println("lastName : " + lastName);
			System.err.println("birth : " + birth);

			if (firstName.isBlank() || lastName.isBlank() || birth.isBlank()) {
				Msg.error("이름 또는 생년월일을 입력해주세요.");
				return;
			}

			String pattern = "^[a-zA-Z]+$";
			if (!Pattern.matches(pattern, firstName) || !Pattern.matches(pattern, lastName)) {
				Msg.error("영문 이름은 영문만 가능합니다.");
				jtfFristName.setText("");
				jtfLastName.setText("");
				jtfFristName.requestFocus();
				return;
			}

			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setLenient(false);

			try {
				Date data = sdf.parse(birth);

				if (data.after(now)) {
					Msg.error("생년월일을 확인해주세요.");
					jtfBirth.setText("");
					return;

				}

			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				Msg.error("생년월일을 확인해주세요.");
				jtfBirth.setText("");
				return;
			}

			String name = firstName + " " + lastName;
			String grade = "";
			int year = Integer.parseInt(birth.split("-")[0]);

			if (2023 - year - 1 > 5) {
				grade = "성인";
			} else if (2023 - year - 1 >= 1) {
				grade = "소아";
			} else {
				grade = "유아";
			}

			jtbCenter.addData(name, birth, grade);
			jtfFristName.setText("");
			jtfLastName.setText("");
			jtfBirth.setText("");

//			for (Vector<String> row : jtbCenter.data) {
//				System.out.println(row);
//			}

		});

		jbDelete.addActionListener(e -> {

			int row = jtbCenter.jtable.getSelectedRow();

			if (row == -1) {
				Msg.error("삭제팔 레코드가 없거나 선택되지 않았습니다.");
				return;
			}

			jtbCenter.dtm.removeRow(row);

		});

		jbDone.addActionListener(e -> {

			int rowCnt = jtbCenter.dtm.getRowCount();

			if (rowCnt == 0) { // 탑승객 0명인 경우 리턴
				Msg.error("탑승객을 1명 이상 입력하세요.");
				return;
			}

			Vector<Vector> data = jtbCenter.dtm.getDataVector();

			for (Vector row : data) {

				System.out.println(row.get(2));

				if (row.get(2).equals("성인")) {
					// 소아 유아가 있을 경우 성인을 반드시 선택하도록 하라 -> 그냥 성인이 무조건 있으면 된다.
					// 때문에 반복하여 성인만 나오면 성공이다.
//					Msg.info("성공");

//					new SeatChoseForm(data);
					new SeatChoseForm2(data);
					return;
				}

			}

			// 끝까지 돌렸는데 성인이 없을 경우 에러 처리
			Msg.error("소아 / 유아가 있을 경우 성인을 반드시 포함하여야 합니다.");
			return;

		});

	}

}
