package bases;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

public class NullPanel extends BasePanel {
	public NullPanel(int width, int hegiht, JLabel lb1, int x1, int y1, int w1, int h1, JLabel lb2, int x2, int y2,
			int w2, int h2) {
		// TODO Auto-generated constructor stub
		super();
		super.setLayout(null);

		super.setPreferredSize(new DimensionUIResource(width, hegiht));

		super.add(lb2);
		super.add(lb1);

		super.setLine();

		lb1.setBounds(x1, y1, w1, h1);
		lb2.setBounds(x2, y2, w2, h2);

	}
}
