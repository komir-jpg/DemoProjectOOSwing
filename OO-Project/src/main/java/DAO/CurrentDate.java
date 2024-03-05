package DAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentDate {

	public CurrentDate() {
		
	}
	public String date() {
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		return dateformat.format(calendar.getTime());
	}

}
