package windows;

import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import UserModel.UserModel;
import bases.BaseFrame;
import bases.BaseLabel;
import bases.BaseTable;
import jdbc.DbManager;

public class MyPageForm extends BaseFrame {
	private BaseLabel jlUser;
	private JRadioButton jrbBorrowList;
	private JRadioButton jrbLikeBook;
	private ButtonGroup jrbGroup;
	private BaseTable jtbCenter;
	private String allDataSize;
	private String returnDone;
	private String returned;
	private String returnIng;
	private BaseLabel jlUserInfo;
	private JButton jbDelete;
	private JButton jbPdf;

	public MyPageForm() {
		// TODO Auto-generated constructor stub
		setFrame("마이페이지", 700, 400);
	}

	@Override
	public void made() {
		// TODO Auto-generated method stub
		jlUser = new BaseLabel("회원 : " + UserModel.userData.get(1));
		jrbBorrowList = new JRadioButton("대출내역");
		jrbLikeBook = new JRadioButton("관심도서");

		jrbGroup = new ButtonGroup();
		jrbGroup.add(jrbBorrowList);
		jrbGroup.add(jrbLikeBook);

		jlUserInfo = new BaseLabel("총 대여이력 : " + allDataSize + "권  반납완료 : " + returnDone + "연체 중 : " + returned
				+ "권  대출 중 : " + returnIng + "권");

		jbDelete = new JButton("삭제하기");
		jbPdf = new JButton("pdf출력");

	}

	@Override
	public void design() {
		// TODO Auto-generated method stub

		jpTop.addChild();
		jpTop.jpLeft.setFlowLeft();
		jpTop.jpLeft.add(jlUser);
		jpTop.jpRight.setFlowRight();
		jpTop.jpRight.add(jrbBorrowList);
		jpTop.jpRight.add(jrbLikeBook);

		updateBorrowList();

		jpBottom.addChild();
		jpBottom.jpLeft.setFlowLeft();
		jpBottom.jpLeft.add(jlUserInfo);
		jpBottom.jpRight.setFlowRight();
		jpBottom.jpRight.add(jbDelete);
		jpBottom.jpRight.add(jbPdf);

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
	}

	private void updateBorrowList() {
		// TODO Auto-generated method stub
		
		Vector<Vector<String>> borrowBookList = DbManager.db.getDb("SELECT \r\n"
				+ "	b.b_name, \r\n"
				+ "    concat(r.r_reading, '/', b.b_page),\r\n"
				+ "    r_date,\r\n"
				+ "    if(\r\n"
				+ "		r.r_returnday = '0000-00-00' or isnull(r.r_returnday),\r\n"
				+ "			date_add(r.r_date, interval 14 + r_count day),\r\n"
				+ "				r.r_returnday\r\n"
				+ "        ),\r\n"
				+ "	if(\r\n"
				+ "		r.r_returnday = '0000-00-00' or isnull(r.r_returnday),\r\n"
				+ "			if(\r\n"
				+ "				date_add(r.r_date, interval 14 + r_count day) > now(),\r\n"
				+ "					'연체중',\r\n"
				+ "						'대출중'\r\n"
				+ "                ),\r\n"
				+ "				'반납완료'\r\n"
				+ "    ),\r\n"
				+ "    r.r_no, b.b_no\r\n"
				+ "FROM 2023지방_2.rental as r\r\n"
				+ "	join book as b\r\n"
				+ "		on r.b_no = b.b_no\r\n"
				+ "where u_no = ?;", UserModel.userData.get(0));
		
		jtbCenter = new BaseTable(borrowBookList, "도서명", "읽은 페이지", "대출일", "반납일", "대출상태", "", "");
		
		jpCenter.add(jtbCenter);

	}

}
