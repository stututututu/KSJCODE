package run;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Pattern;

import bases.BaseFrame;
import bases.BaseTable;

public class test extends BaseFrame {
	public static void main(String[] args) {
//		String pattern = "^[a-zA-Z]+$";
//
//		String pw = "asdf";
//
//		System.out.println(Pattern.matches(pattern, pw));

//		HashMap<Integer, String> a = new HashMap<>();
//		
//		a.put(1, "Ff");
//		
//		System.out.println(a.get(1));
//
//		a.put(1, "xx");
//
//		System.out.println(a.get(1));
//

		new test();

	}

	private BaseTable jt;
	private Vector<Vector<String>> data;
	private Vector<String> row1;
	private Vector<String> row2;
	private Vector<String> row3;
	private Vector<String> row4;

	public test() {
		// TODO Auto-generated constructor stub
		setFrame("test", 500, 500);

	}

	@Override
	public void make() {
		// TODO Auto-generated method stub

		data = new Vector<Vector<String>>();

		row1 = new Vector<String>();
		row2 = new Vector<String>();
		row3 = new Vector<String>();
		row4 = new Vector<String>();

		row1.add("1");
		row1.add("2");
		row1.add("3");
		row1.add("4");

		row2.add("1");
		row2.add("2");
		row2.add("3");
		row2.add("4");

		row3.add("1");
		row3.add("2");
		row3.add("3");
		row3.add("4");

		row4.add("1");
		row4.add("2");
		row4.add("3");
		row4.add("4");

		data.add(row1);
		data.add(row2);
		data.add(row3);
		data.add(row4);

		jt = new BaseTable(data, "열1", "열2", "열3", "열4");

		
		

		
	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpCenter.add(jt);
	}

	@Override
	public void event() {
		// TODO Auto-generated method stub

	}
}
