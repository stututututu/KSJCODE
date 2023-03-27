package res;

import java.util.Date;
import java.util.Vector;

public class AirTicketManager {
	public static String s_no;
	public static String month;
	public static String day;
	public static String depart;
	public static String arrival;
	public static String departString;
	public static String arrivalString;
	public static Date date;
	public static Vector<String> rData;

	public void setNull() {
		// TODO Auto-generated method stub
		AirTicketManager.s_no = "";
		AirTicketManager.month = "";
		AirTicketManager.day = "";
		AirTicketManager.depart = "";
		AirTicketManager.arrival = "";
		AirTicketManager.departString = "";
		AirTicketManager.arrivalString = "";
		AirTicketManager.date = null;
	}

}
