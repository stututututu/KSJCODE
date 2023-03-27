package run;

import jdbc.DbCreate;

public class Main {
	public static void main(String[] args) {
		new DbCreate();
	}
}



//-- java DbCreate -> set global sql_mode = '';
//-- set sql_mode = '';