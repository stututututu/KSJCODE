package windows;

import java.util.Vector;

import javax.swing.JTextField;

import bases.BaseButton;
import bases.BaseFrame;
import bases.BaseImgLabel;
import bases.BaseLabel;
import bases.BasePanel;
import jdbc.DbManager;
import messge.Msg;
import model.UserModel;

public class MainForm extends BaseFrame {
	private JTextField jtfId;
	private JTextField jtfPw;
	private BaseButton jbLogin;
	private BaseButton jbSignUp;
	private BaseButton jbLandGraph;
	private BaseButton jbLogout;
	private BaseButton jbInfoUpdate;
	private BaseButton jbAirSearch;
	private BaseButton jbMyPage;
	private BaseButton jbNumGraph;
	private BasePanel jpTmp;

	public MainForm() {
		// TODO Auto-generated constructor stub
		setFrame("메인", 600, 500);
	}

	@Override
	public void make() {
		// TODO Auto-generated method stub
		jtfId = new JTextField();
		jtfPw = new JTextField();

		jbLogin = new BaseButton("로그인");
		jbSignUp = new BaseButton("회원가입");
		jbLandGraph = new BaseButton("대륙별분석");

		jbLogout = new BaseButton("로그아웃");
		jbInfoUpdate = new BaseButton("정보수정");
		jbAirSearch = new BaseButton("항공권 조회");
		jbMyPage = new BaseButton("마이페이지");
		jbNumGraph = new BaseButton("연령별분석");
	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpTop.addChild();

		jpTop.jpTop.add(new BaseImgLabel("SKY AIRLINE", "./datafiles/구름.jpg", 600, 100).setTextFontSize(20)
				.setTextCenter().setTextLocateBottom());

		jpTop.jpCenter.add(new BaseImgLabel("", "./datafiles/비행기.jpg", 200, 200));

		jpCenter.addChild();
		jpCenter.jpLeft.setGrid(2, 1, 0, 20);
		jpCenter.jpLeft.add(new BaseLabel("ID : "));
		jpCenter.jpLeft.add(new BaseLabel("PW : "));

		jpCenter.jpCenter.setGrid(2, 1, 0, 20);
		jpCenter.jpCenter.add(jtfId);
		jpCenter.jpCenter.add(jtfPw);

		logout();
//		login();

		jpCenter.setEmtyBorder(10, 200, 10, 200);
		jpMain.setEmtyBorder(0, 0, 15, 0);

		// 로그인 테스트 상태
//		Vector<Vector<String>> userData = DbManager.db.getDb("SELECT * FROM 2023지방_3.user;");
//		UserModel.login(userData.get(0));
//		logRefresh();

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		jbLogin.addActionListener(e -> {
			String id = jtfId.getText();
			String pw = jtfPw.getText();

			System.out.println("id : " + id);
			System.out.println("pw : " + pw);

			if (id.isBlank() || pw.isBlank()) {
				Msg.error("공백이 있습니다.");
				return;
			}
			Vector<Vector<String>> userData = DbManager.db.getDb("SELECT * FROM 2023지방_3.user where id = ? and pw = ?;",
					id, pw);
			if (userData.size() == 0) {
				Msg.error("ID 또는 PW를 확인하세요.");
				jtfId.setText("");
				jtfPw.setText("");
				jtfId.requestFocus();
				return;
			}
			UserModel.login(userData.get(0));
			Msg.info(UserModel.userData.get(3) + "님 환영합니다.");
			jtfId.setText("");
			jtfPw.setText("");
			logRefresh();

		});

		jbAirSearch.addActionListener(e -> {
			new AirSearchForm();
		});

		jbSignUp.addActionListener(e -> {
			new SignUpForm();
		});

		jbInfoUpdate.addActionListener(e -> {
			new InfoUpdateForm();
		});

		jbLogout.addActionListener(e -> {
			UserModel.logout();
			logRefresh();
		});

		jbMyPage.addActionListener(e -> {
			new MyPageForm();
		});

		jbLandGraph.addActionListener(e -> {
			new LandGrpahForm();
		});
	}

	public void logRefresh() {
		// TODO Auto-generated method stub
		if (UserModel.userData != null) {
			login();
			return;
		}

		logout();

	}

	public void logout() {
		// TODO Auto-generated method stub
		jpBottom.removeAll();

		jpTmp = new BasePanel().setFlowCenter();
		jpTmp.add(jbLogin);
		jpTmp.add(jbSignUp);
		jpTmp.add(jbLandGraph);

		jpBottom.add(jpTmp);

		super.setSize(600, 500);

		super.refresh();
	}

	public void login() {
		// TODO Auto-generated method stub
		jpBottom.removeAll();

		jpTmp = new BasePanel().setFlowCenter();
		jpTmp.add(jbLogout);
		jpTmp.add(jbInfoUpdate);
		jpTmp.add(jbAirSearch);
		jpTmp.add(jbMyPage);
		jpTmp.add(jbNumGraph);

		jpBottom.add(jpTmp);

		super.setSize(600, 430);

		super.refresh();
	}

}
