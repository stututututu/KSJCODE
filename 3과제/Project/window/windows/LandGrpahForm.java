package windows;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;

import bases.BaseComboBox;
import bases.BaseFrame;
import bases.BaseLabel;
import bases.BasePanel;
import jdbc.DbManager;

public class LandGrpahForm extends BaseFrame {
	private BaseComboBox jcbLand;
	private Vector<Vector<String>> landNameData;
	private Vector<Vector<String>> graphData;
	private BaseLabel jpCenterGraph;

	public LandGrpahForm() {
		// TODO Auto-generated constructor stub
		setFrame("대륙별 분석", 646, 392);
	}

	@Override
	public void make() {
		// TODO Auto-generated method stub

		landNameData = DbManager.db.getDb("SELECT c.c_name, c.* FROM 2023지방_3.continent as c;");
		jcbLand = new BaseComboBox("세계", landNameData);
	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpTop.setFlowLeft();
		jpTop.add(new BaseLabel("대륙").setTextFontSize(15));
		jpTop.add(jcbLand);

		jcbLandChange();
	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		jcbLand.addActionListener(e -> {

			jcbLandChange();

		});
	}

	private void jcbLandChange() {
		// TODO Auto-generated method stub

		jpCenter.removeAll();

		int c_no = jcbLand.getSelectedIndex();

		if (c_no == 0) {
			graphData = DbManager.db.getDb("SELECT \r\n" + "	c.c_no, c.c_name, c.x, c.y, count(c.c_no)\r\n"
					+ "FROM 2023지방_3.reservation as r\r\n" + "	join schedule as s\r\n" + "		on r.s_no = s.s_no\r\n"
					+ "	join nation as n\r\n" + "		on n.n_no = s.arrival\r\n" + "	join continent as c\r\n"
					+ "		on c.c_no = n.c_no\r\n" + "	group by c.c_no\r\n" + ";");

		} else {
			graphData = DbManager.db.getDb(
					"SELECT \r\n" + "	n.n_no, n.n_name, n.x, n.y,  count(n.n_no),  c.c_no, c.c_name\r\n"
							+ "FROM 2023지방_3.reservation as r\r\n" + "	join schedule as s\r\n"
							+ "		on r.s_no = s.s_no\r\n" + "	join nation as n\r\n"
							+ "		on n.n_no = s.arrival\r\n" + "	join continent as c\r\n"
							+ "		on c.c_no = n.c_no\r\n" + "where c.c_no = ?\r\n" + "	group by n.n_no\r\n" + ";",
					c_no);

		}

		float fillOvalSize = (c_no == 0f ? 0.005f : 0.05f);

		jpCenterGraph = new BaseLabel() {
			@Override
			public void paint(Graphics g) {
				// TODO Auto-generated method stub
				super.paint(g);
				g.setColor(Color.red);

				for (Vector<String> row : graphData) {

					int size = (int) (Integer.parseInt(row.get(4)) * fillOvalSize);

					int x = Integer.parseInt(row.get(2)) - (size / 2);
					int y = Integer.parseInt(row.get(3)) - (size / 2);

					g.fillOval(x, y, size, size);

				}

			}
		};

		System.out.println(jpCenterGraph.getSize());

		jpCenterGraph.addImg("./datafiles/지도/" + c_no + ".jpg", 1133, 670);

		jpCenter.add(jpCenterGraph);

		super.refresh();
	}
}
