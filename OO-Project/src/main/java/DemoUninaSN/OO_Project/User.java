package DemoUninaSN.OO_Project;

import java.util.Date;

public class User {
	
	private String name;
	private String surname;
	private String sex;
	private Date subscriptionDate;
	private String userName;
	private String email;
	private String password;
	private String userType;
	
	
	
	public User() {
		
	}
	


	public User(String name, String surname, String sex, String userName,
			String email, String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}



	public void setName(String name) {
		this.name = name;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}


	public void setSubsriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	
	public String getName() {
		return name;
	}


	public String getSurname() {
		return surname;
	}


	public String getSex() {
		return sex;
	}


	public Date getSubcsriptionDate() {
		return subscriptionDate;
	}


	public String getUserName() {
		return userName;
	}


	public String getEmail() {
		return email;
	}


	public String getPassword() {
		return password;
	}


	public String getUserType() {
		return userType;
	}




	@Override
	public String toString() {
		return "nome->"+this.name+"\r\n"+
			   "cognome->"+this.surname+"\r\n"+
			   "sesso->"+this.sex+"\r\n"+
			   "username->"+this.userName+"\r\n"+
			   "email->"+this.email;
	}



	@Override
	public boolean equals(Object obj) {
		String ComparedUser;
		User user = (User)obj;
		
		ComparedUser = user.getUserName();
		
		return ComparedUser.compareTo(this.userName) == 0;
	}
	
	

}
