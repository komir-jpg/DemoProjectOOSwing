package utils;

public class DataBaseResult {
	private int count;
	private int year;
	private int month;
	
	public DataBaseResult(int count,int year,int month) {
		this.count = count;
		this.month = month;
		this.year = year;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}
	public String convertMonth() {
		String convertedMonth;
		switch (month) {
		case 1: {
			convertedMonth = "gennaio";
			break;
		}
		case 2:{
			convertedMonth = "febbraio";
			break;
		}
		case 3:{
			convertedMonth = "marzo";
			break;
		}
		case 4:{
			convertedMonth = "aprile";
			break;
		}
		case 5:{
			convertedMonth = "maggio";
			break;
		}
		case 6:{
			convertedMonth = "giugno";
			break;
		}
		case 7:{
			convertedMonth = "luglio";
			break;
		}
		case 8:{
			convertedMonth = "agosto";
			break;
		}
		case 9:{
			convertedMonth = "settembre";
			break;
		}
		case 10:{
			convertedMonth = "ottobre";
			break;
		}
		case 11:{
			convertedMonth = "novembre";
			break;
		}
		case 12:{
			convertedMonth = "dicembre";
			break;
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + month);
		}
		return convertedMonth;
	}
}
