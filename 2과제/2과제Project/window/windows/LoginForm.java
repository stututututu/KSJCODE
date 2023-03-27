package windows;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import UserModel.UserModel;
import bases.BaseFrame;
import bases.BaseLabel;
import jdbc.DbManager;
import messge.Msg;

public class LoginForm extends BaseFrame {
	private JTextField jtfId;
	private JPasswordField jtfPw;
	private JButton jbLogin;
	private int errCnt;
	private MainForm mainForm;

	public LoginForm(MainForm mainForm) {
		// TODO Auto-generated constructor stub
		this.mainForm = mainForm;
		setFrame("로그인", 350, 220);
	}

	@Override
	public void made() {
		// TODO Auto-generated method stub
		jtfId = new JTextField();
		jtfPw = new JPasswordField();

		jbLogin = new JButton("로그인");
	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpTop.add(new BaseLabel("로그인", 25).setTextCenter());
		jpCenter.addChild();
		jpCenter.jpLeft.setGrid(2, 1, 0, 10);
		jpCenter.jpLeft.add(new BaseLabel("아이디"));
		jpCenter.jpLeft.add(new BaseLabel("비밀번호"));

		jpCenter.jpCenter.setGrid(2, 1, 0, 10);
		jpCenter.jpCenter.add(jtfId);
		jpCenter.jpCenter.add(jtfPw);

		jpBottom.add(jbLogin);

		jpCenter.setBorder(10, 0, 15, 0);
		jpMain.setBorder(15, 15, 15, 15);

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		errCnt = 0;

		jbLogin.addActionListener(e -> {
			String id = jtfId.getText();
			String pw = jtfPw.getText();

			System.out.println("id : " + id);
			System.out.println("pw : " + pw);

			if (id.isBlank() || pw.isBlank()) {
				Msg.error("빈칸이 있습니다.");
				errCnt += 1;

				if (errCnt >= 3) {
					Msg.error("3회 오류로 종료합니다.");
					close();
					return;

				}

				return;
			}
			if (id.equals("admin") && pw.equals("1234")) {
				UserModel.logout();
				mainForm.formRefresh(true);
				close();
				return;
			}
			Vector<Vector<String>> userData = DbManager.db
					.getDb("SELECT * FROM 2023지방_2.user where u_id = ? and u_pw = ?;", id, pw);
			if (userData.size() == 0) {
				Msg.error("아이디 또는 비밀번호를 확인하세요.");
				errCnt += 1;

				jtfId.setText("");
				jtfPw.setText("");

				jtfId.requestFocus();

				if (errCnt >= 3) {
					Msg.error("3회 오류로 종료합니다.");
					close();
					return;

				}

				return;

			}

			UserModel.login(userData.get(0));
			mainForm.formRefresh(false);
			close();

		});
	}

}
