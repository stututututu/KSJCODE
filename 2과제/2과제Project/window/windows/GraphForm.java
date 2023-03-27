package windows;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JTextArea;

import bases.BaseFrame;
import bases.BaseLabel;
import bases.BasePanel;
import bases.BaseTextArea;
import jdbc.DbManager;

public class GraphForm extends BaseFrame {
	private BasePanel jpGraph;
	private Vector<Color> colors;

	public GraphForm() {
		// TODO Auto-generated constructor stub
		setFrame("통계", 489, 443);
	}

	@Override
	public void made() {
		// TODO Auto-generated method stub

		colors = new Vector<Color>();
		colors.add(Color.red);
		colors.add(Color.orange);
		colors.add(Color.yellow);
		colors.add(Color.green);
		colors.add(Color.blue);

		jpGraph = new BasePanel();
	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpTop.add(new BaseLabel("인기도서", 25).setTextCenter());
		jpCenter.add(jpGraph);

		jpGraph.setGrid(1, 5, 40, 0);
		jpBottom.setGrid(1, 5, 40, 0);

		changeGraph();

		jpMain.setBorder(15, 30, 10, 30);

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub

	}

	private void changeGraph() {
		// TODO Auto-generated method stub

		jpGraph.removeAll();
		jpBottom.removeAll();

		Vector<Vector<String>> bookRank = DbManager.db.getDb("SELECT count(b.b_no), b.* FROM 2023지방_2.rental as r\r\n"
				+ "	join book as b\r\n" + "		on r.b_no = b.b_no\r\n" + "    group by b.b_no\r\n"
				+ "    order by count(b.b_no) desc, b.b_no\r\n" + "    limit 5; ");

		for (int i = 0; i < bookRank.size(); i++) {

			int bookReadCnt = Integer.parseInt(bookRank.get(i).get(0));

			BasePanel tmpGraph = new BasePanel();
			tmpGraph.addChild();

			tmpGraph.jpTop.add(new BaseLabel(bookReadCnt + "건").setTextCenter());
			tmpGraph.jpCenter.setLine().setBackground(colors.get(i));

			tmpGraph.jpTop.setBorder(255 - (bookReadCnt * 12), 0, 0, 0);

			jpGraph.add(tmpGraph);
			jpBottom.add(new BaseTextArea(bookRank.get(i).get(2)));

		}

	}

}
