package bases;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public abstract class BaseFrame extends JFrame implements BaseI {
	public BasePanel jpMain;
	public BasePanel jpTop;
	public BasePanel jpLeft;
	public BasePanel jpCenter;
	public BasePanel jpRight;
	public BasePanel jpBottom;
	private String title;

	public void setFrame(String title, int width, int height) {
		// TODO Auto-generated method stub
		this.title = title;
		super.setTitle(title);
		super.setSize(width, height);

		jpMain = new BasePanel();
		jpTop = new BasePanel();
		jpLeft = new BasePanel();
		jpCenter = new BasePanel();
		jpRight = new BasePanel();
		jpBottom = new BasePanel();

		made();
		design();
		event();

		jpMain.add(jpTop, BorderLayout.NORTH);
		jpMain.add(jpLeft, BorderLayout.WEST);
		jpMain.add(jpCenter, BorderLayout.CENTER);
		jpMain.add(jpRight, BorderLayout.EAST);
		jpMain.add(jpBottom, BorderLayout.SOUTH);

		super.add(jpMain);

		super.setIconImage(new ImageIcon("./datafiles/logo.png").getImage());

		super.setLocationRelativeTo(null);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		super.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);

				System.out.println(title + " : " + e.getWindow().getWidth() + "," + e.getWindow().getHeight());

			}

		});

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		super.dispose();

	}

	public void refresh() {
		// TODO Auto-generated method stub
		super.repaint();
		super.validate();
	}
}
