package model;

import java.util.Vector;

import jdbc.DbManager;

public class DataModel {

	public static Vector<Vector<String>> allData() {
		// TODO Auto-generated method stub
		Vector<String> row1 = new Vector<String>();
		row1.add("");
		row1.add("0");

		Vector<Vector<String>> data = DbManager.db.getDb("SELECT n_name, n_no FROM 2023지방_3.nation;");

		Vector<Vector<String>> result = new Vector<Vector<String>>();
		result.add(row1);

//		for (Vector<String> row : data) {
//			result.add(row);
//		}

		result.addAll(data);

		return result;
	}

}
