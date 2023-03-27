package testProject;

import java.util.Vector;

public class Run {
	
	public Run() {
		// TODO Auto-generated constructor stub
		

		Vector<Vector<String>> db = DbManager.db.getData("" + "SELECT \r\n" + "   b.b_name, \r\n"
				+ "    concat(r.r_reading, '/', b.b_page),\r\n" + "    r_date,\r\n" + "    if(\r\n"
				+ "      r.r_returnday = '0000-00-00' or isnull(r.r_returnday),\r\n"
				+ "         date_add(r.r_date, interval 14 + r_count day),\r\n" + "            r.r_returnday\r\n"
				+ "        ),\r\n" + "   if(\r\n" + "      r.r_returnday = '0000-00-00' or isnull(r.r_returnday),\r\n"
				+ "         if(\r\n" + "            date_add(r.r_date, interval 14 + r_count day) > now(),\r\n"
				+ "               '연체중',\r\n" + "                  '대출중'\r\n" + "                ),\r\n"
				+ "            '반납완료'\r\n" + "    ),\r\n" + "    r.r_no, b.b_no\r\n" + "FROM 2023지방_2.rental as r\r\n"
				+ "   join book as b\r\n" + "      on r.b_no = b.b_no\r\n" + "where u_no = 1;");

		System.out.println(db);
		
		
	}
	public static void main(String[] args) {
		System.out.println("Dd");
		
//		new DbCreate();
		
		new Run();
	}
}
