package bases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class BasePanel extends JPanel {
	public BasePanel jpTop;
	public BasePanel jpLeft;
	public BasePanel jpCenter;
	public BasePanel jpRight;
	public BasePanel jpBottom;

	public BasePanel() {
		// TODO Auto-generated constructor stub
		super.setBackground(Color.white);
		super.setLayout(new BorderLayout());
	}

	public BasePanel addChild() {
		// TODO Auto-generated method stub
		jpTop = new BasePanel();
		jpLeft = new BasePanel();
		jpCenter = new BasePanel();
		jpRight = new BasePanel();
		jpBottom = new BasePanel();

		super.add(jpTop, BorderLayout.NORTH);
		super.add(jpLeft, BorderLayout.WEST);
		super.add(jpCenter, BorderLayout.CENTER);
		super.add(jpRight, BorderLayout.EAST);
		super.add(jpBottom, BorderLayout.SOUTH);

		return this;
	}

	public BasePanel setLine() {
		// TODO Auto-generated method stub
		super.setBorder(BorderFactory.createLineBorder(Color.black));
		return this;
	}

	public BasePanel setLine(String title) {
		// TODO Auto-generated method stub
		super.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black), title));
		return this;
	}

	public BasePanel setGrid(int rows, int cols, int hgap, int vgap) {
		// TODO Auto-generated method stub
		super.setLayout(new GridLayout(rows, cols, hgap, vgap));
		return this;
	}

	public BasePanel setFlowLeft() {
		// TODO Auto-generated method stub
		super.setLayout(new FlowLayout(FlowLayout.LEFT));
		return this;
	}

	public BasePanel setFlowCenter() {
		// TODO Auto-generated method stub
		super.setLayout(new FlowLayout(FlowLayout.CENTER));
		return this;
	}

	public BasePanel setFlowRight() {
		// TODO Auto-generated method stub
		super.setLayout(new FlowLayout(FlowLayout.RIGHT));
		return this;
	}

	public BasePanel setBorder(int top, int left, int bottom, int right) {
		// TODO Auto-generated method stub
		super.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
		return this;
	}

//	public BasePanel setLine(String title, int size) {
//		// TODO Auto-generated method stub
//		super.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black), title));
//		super.setFont(new Font("HY헤드라인M", Font.PLAIN, size));
//
//		return this;
//	}

}
