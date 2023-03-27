package model;

import java.util.Vector;

import jdbc.DbManager;

public class DataModel {

	public static Vector<Vector<String>> allType() {
		// TODO Auto-generated method stub
		Vector<Vector<String>> result = new Vector<Vector<String>>();

		Vector<Vector<String>> data = DbManager.db.getDb("SELECT d_name, d_no FROM 2023지방_2.division;");

		Vector<String> row1 = new Vector<String>();
		row1.add("전체");
		row1.add("0");

		result.add(row1);
		for (Vector<String> row : data) {
			result.add(row);
		}

		return result;
	}

	public static Vector<Vector<String>> blankType() {
		// TODO Auto-generated method stub
		Vector<Vector<String>> result = new Vector<Vector<String>>();

		Vector<Vector<String>> data = DbManager.db.getDb("SELECT d_name, d_no FROM 2023지방_2.division;");

		Vector<String> row1 = new Vector<String>();
		row1.add("");
		row1.add("0");

		result.add(row1);
		for (Vector<String> row : data) {
			result.add(row);
		}

		return result;
	}

}
