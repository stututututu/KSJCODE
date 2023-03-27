package bases;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public abstract class BaseFrame extends JFrame implements BaseI {
	public BasePanel jpMain;
	public BasePanel jpTop;
	public BasePanel jpLeft;
	public BasePanel jpCenter;
	public BasePanel jpRight;
	public BasePanel jpBottom;

	public void setFrame(String title, int width, int height) {
		// TODO Auto-generated method stub
		super.setTitle(title);
		super.setSize(width, height);

		jpMain = new BasePanel();
		jpTop = new BasePanel();
		jpLeft = new BasePanel();
		jpCenter = new BasePanel();
		jpRight = new BasePanel();
		jpBottom = new BasePanel();

		make();
		design();
		event();

		jpMain.add(jpTop, BorderLayout.NORTH);
		jpMain.add(jpLeft, BorderLayout.WEST);
		jpMain.add(jpCenter, BorderLayout.CENTER);
		jpMain.add(jpRight, BorderLayout.EAST);
		jpMain.add(jpBottom, BorderLayout.SOUTH);

		super.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				int width = e.getWindow().getWidth();
				int height = e.getWindow().getHeight();

				System.out.println(width + ", " + height);
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}
		});

		super.add(jpMain);

		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		super.setLocationRelativeTo(null);
	}

	public void refresh() {
		// TODO Auto-generated method stub
		super.repaint();
		super.validate();
	}
}
