package windows;

import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JTextField;

import bases.BaseFrame;
import bases.BaseLabel;
import jdbc.DbManager;
import messge.Msg;

public class SignUpForm extends BaseFrame {
	private JTextField jtfName;
	private JTextField jtfId;
	private JTextField jtfPw;
	private JButton jbSignUp;

	public SignUpForm() {
		// TODO Auto-generated constructor stub
		setFrame("회원가입", 350, 270);
	}

	@Override
	public void made() {
		// TODO Auto-generated method stub
		jtfName = new JTextField();
		jtfId = new JTextField();
		jtfPw = new JTextField();

		jbSignUp = new JButton("회원가입");
	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpTop.add(new BaseLabel("회원가입", 25).setTextCenter());
		jpCenter.addChild();
		jpCenter.jpLeft.setGrid(3, 1, 0, 10);
		jpCenter.jpLeft.add(new BaseLabel("이름"));
		jpCenter.jpLeft.add(new BaseLabel("아이디"));
		jpCenter.jpLeft.add(new BaseLabel("비밀번호"));

		jpCenter.jpCenter.setGrid(3, 1, 0, 10);
		jpCenter.jpCenter.add(jtfName);
		jpCenter.jpCenter.add(jtfId);
		jpCenter.jpCenter.add(jtfPw);

		jpBottom.add(jbSignUp);

		jpMain.setBorder(15, 15, 15, 15);
		jpCenter.setBorder(10, 0, 10, 0);

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		jbSignUp.addActionListener(e -> {
			String name = jtfName.getText();
			String id = jtfId.getText();
			String pw = jtfPw.getText();

			System.out.println("name : " + name);
			System.out.println("id : " + id);
			System.out.println("pw : " + pw);

			if (name.isBlank() || id.isBlank() || pw.isBlank()) {
				Msg.error("빈칸이 있습니다.");
				return;
			}
			String patternName = "^(?=.*[가-힣]).{2,}$";
			if (!Pattern.matches(patternName, name)) {
				Msg.error("이름은 한글로 2글자 이상만 입력 가능합니달.");
				jtfName.setText("");
				jtfName.requestFocus();
				return;
			}
			if (!Pattern.matches(patternName, name)) {
				Msg.error("이름은 한글로 2글자 이상만 입력 가능합니달.");
				jtfName.setText("");
				jtfName.requestFocus();
				return;
			}
			Vector<Vector<String>> idData = DbManager.db.getDb("SELECT * FROM 2023지방_2.user where u_id = ?;", id);
			if (idData.size() != 0 || id.equals("admin")) {
				Msg.error("이미 있는 아이디 입니다.");
				jtfId.setText("");
				jtfId.requestFocus();
				return;
			}
			String patternPw = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$]).{6,}$";
			if (!Pattern.matches(patternPw, pw)) {
				Msg.error("비밀번호 형식이 일치하지 않습니다.");
				jtfPw.setText("");
				jtfPw.requestFocus();
				return;
			}
			for (int i = 0; i < pw.length() - 1; i++) {
				String st1 = pw.substring(i, i + 1);
				String st2 = pw.substring(i + 1, i + 1 + 1);

				if (st1.equals(st2)) {
					Msg.error("비밀번호는 연속으로 같은 글자가 올 수 없습니다..");
					return;
				}

			}

			Msg.info(name + "님 회원가입이 완료되었습니다.");
			DbManager.db.setDb("INSERT INTO `2023지방_2`.`user` (`u_name`, `u_id`, `u_pw`) VALUES (?, ?, ?);", name, id,
					pw);

			close();

		});
	}

}
