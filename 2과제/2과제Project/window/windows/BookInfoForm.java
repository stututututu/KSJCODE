package windows;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTextArea;

import UserModel.UserModel;
import bases.BaseFrame;
import bases.BaseImageLabel;
import bases.BaseLabel;
import jdbc.DbManager;
import messge.Msg;
import model.ImageModel;

public class BookInfoForm extends BaseFrame {
	private ImageModel row;
	private BaseLabel jlName;
	private BaseImageLabel jlImg;
	private BaseLabel jlBookType;
	private BaseLabel jlAuthor;
	private BaseLabel jlBookCnt;
	private JTextArea jlExp;
	private JButton jbBorrow;

	public BookInfoForm(ImageModel row) {
		// TODO Auto-generated constructor stub
		this.row = row;
		setFrame("도서정보", 350, 400);
	}

	@Override
	public void made() {
		// TODO Auto-generated method stub
		jlName = new BaseLabel(row.getData().get(1), 25);
		jlImg = new BaseImageLabel("", row.getIcon(), 170, 210);
		jlBookType = new BaseLabel("자연과학", 18).setLine();
		jlAuthor = new BaseLabel("저자 : " + row.getData().get(3)).setLine(0, 0, 3, 0);
		jlBookCnt = new BaseLabel("재고 : " + row.getData().get(4) + "권 / 페이지 : " + row.getData().get(5) + "p").setLine(0,
				0, 3, 0);

		jlExp = new JTextArea(row.getData().get(6));
		jlExp.setEditable(false);
		jlExp.setLineWrap(true);

		jbBorrow = new JButton("대출");

		if (row.getData().get(4).equals("0")) {
			jbBorrow.setEnabled(false);
		}
	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpTop.setFlowLeft().add(jlName);
		jpCenter.addChild();
		jpCenter.jpLeft.add(jlImg);
		jpCenter.jpCenter.addChild();
		jpCenter.jpCenter.jpTop.setFlowLeft().add(jlBookType);

		jpCenter.jpCenter.jpCenter.setGrid(4, 1, 0, 0);

		jpCenter.jpCenter.jpCenter.add(jlAuthor);
		jpCenter.jpCenter.jpCenter.add(jlBookCnt);
		jpCenter.jpCenter.jpCenter.add(new BaseLabel());
		jpCenter.jpCenter.jpCenter.add(new BaseLabel());

		jpBottom.addChild();
		jpBottom.jpCenter.setLine("설명");
		jpBottom.jpCenter.add(jlExp);

		jpBottom.jpCenter.setPreferredSize(new Dimension(345, 85));
		jpBottom.jpBottom.setFlowRight().add(jbBorrow);

//		super.pack();

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		jbBorrow.addActionListener(e -> {

//			Vector<Vector<String>> bookBorrowed = DbManager.db.getDb("SELECT * FROM 2023지방_2.rental \r\n"
//					+ "where u_no = ? and (r_returnday is null or r_returnday = '0000-00-00') and date_add(r_date, interval (r_count + 14) day) < now();",
//					UserModel.userData.get(0));
//			
//			System.out.println(bookBorrowed.size());
//			
//			if (bookBorrowed.size() != 0) {
//				Msg.error("현재 연체 중인 도서가 있습니다.\n도서를 반납하시고 이용해주세요.");
//				return;
//			}

			Vector<Vector<String>> bookBorrowing = DbManager.db.getDb(
					"SELECT * FROM 2023지방_2.rental\r\n" + "where u_no = ? and b_no = ?;", UserModel.userData.get(0),
					row.getData().get(0));
			if (bookBorrowing.size() != 0) {
				Msg.error("이미 대출 중인 도서입니다.");
				return;
			}

			Msg.info("대출이 완료되었습니다.");
			DbManager.db.setDb(
					"INSERT INTO `2023지방_2`.`rental` (`u_no`, `b_no`, `r_date`, `r_count`, `r_reading`, `r_returnday`) VALUES (?, ?, now(), 0, 0, null);",
					UserModel.userData.get(0), row.getData().get(0));

		});
	}

}
