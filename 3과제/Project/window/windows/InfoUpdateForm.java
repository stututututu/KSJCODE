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
import jdbc.DbManager;
import messge.Msg;
import model.UserModel;

public class InfoUpdateForm extends BaseFrame {
	private JTextField jtfId;
	private JTextField jtfPw;
	private JTextField jtfName1;
	private JTextField jtfName2;
	private JTextField jtfBirth;
	private JButton jbInfoUpdate;

	public InfoUpdateForm() {
		// TODO Auto-generated constructor stub
		setFrame("정보수정", 300, 350);
	}

	@Override
	public void make() {
		// TODO Auto-generated method stub
		jtfId = new JTextField();
		jtfPw = new JTextField();
		jtfName1 = new JTextField();
		jtfName2 = new JTextField();
		jtfBirth = new JTextField();

		jbInfoUpdate = new JButton("정보수정");

		jtfId.setEnabled(false);

		jtfId.setText(UserModel.userData.get(1));
		jtfPw.setText(UserModel.userData.get(2));
		jtfName1.setText(UserModel.userData.get(3));
		jtfName2.setText(UserModel.userData.get(4));
		jtfBirth.setText(UserModel.userData.get(5));

	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpTop.add(new BaseLabel("회원가입").setTextFontSize(25).setTextCenter());

		jpCenter.addChild();
		jpCenter.jpLeft.setGrid(5, 1, 0, 15);
		jpCenter.jpLeft.add(new BaseLabel("아이디"));
		jpCenter.jpLeft.add(new BaseLabel("비밀번호"));
		jpCenter.jpLeft.add(new BaseLabel("이름(한글)"));
		jpCenter.jpLeft.add(new BaseLabel("이름(영문)"));
		jpCenter.jpLeft.add(new BaseLabel("생년월일"));

		jpCenter.jpCenter.setGrid(5, 1, 0, 15);
		jpCenter.jpCenter.add(jtfId);
		jpCenter.jpCenter.add(jtfPw);
		jpCenter.jpCenter.add(jtfName1);
		jpCenter.jpCenter.add(jtfName2);
		jpCenter.jpCenter.add(jtfBirth);

		jpBottom.setFlowCenter();
		jpBottom.add(jbInfoUpdate);

		jpCenter.setEmtyBorder(15, 15, 15, 15);
	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		jbInfoUpdate.addActionListener(e -> {
			String pw = jtfPw.getText();
			String name1 = jtfName1.getText();
			String name2 = jtfName2.getText();
			String birth = jtfBirth.getText();

			System.out.println("pw : " + pw);
			System.out.println("name1 : " + name1);
			System.out.println("name2 : " + name2);
			System.out.println("birth : " + birth);

			if (pw.isBlank() || name1.isBlank() || name2.isBlank() || birth.isBlank()) {
				Msg.error("빈칸이 있습니다.");
				return;
			}
			String patternName1 = "^([ㄱ-ㅎㅏ-ㅣ가-힣]+)$";
			if (!Pattern.matches(patternName1, name1)) {
				Msg.error("한글 이름은 한글만 가능합니다.");
				jtfName1.setText("");
				jtfName1.requestFocus();
				return;
			}
			String patternName2 = "^([a-zA-Z ]+)$";
			if (!Pattern.matches(patternName2, name2)) {
				Msg.error("영문 이름은 영문만 가능합니다.");
				jtfName2.setText("");
				jtfName2.requestFocus();
				return;
			}
			String patternName2_ = "^.+ .+$";
			if (!Pattern.matches(patternName2_, name2)) {
				Msg.error("영문 이름은 성과 이름을 구분해주세요.");
				jtfName2.setText("");
				jtfName2.requestFocus();
				return;
			}
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setLenient(false);
			try {
				Date date = sdf.parse(birth);

				if (now.before(date)) {
					Msg.error("생년월일을 확인해주세요.");
					jtfBirth.setText("");
					jtfBirth.requestFocus();
					return;
				}

			} catch (ParseException e1) {
				// TODO Auto-generated catch block
//				e1.printStackTrace();
				Msg.error("생년월일을 확인해주세요.");
				jtfBirth.setText("");
				jtfBirth.requestFocus();
				return;
			}
			Msg.info(name1 + "님 정보가 수정되었습니다.");

			name2 = name2.toUpperCase();
			DbManager.db.setDb(
					"UPDATE `2023지방_3`.`user` SET `pw` = ?, `name1` = ?, `name2` = ?, `birth` = ? WHERE (`u_no` = ?);",
					pw, name1, name2, birth, UserModel.userData.get(0));

			Vector<String> userData = DbManager.db
					.getDb("SELECT * FROM 2023지방_3.user where u_no = ?;", UserModel.userData.get(0)).get(0);

			UserModel.login(userData);

			super.dispose();
		});
	}

}
