package DemoUninaSN.OO_Project;

import java.util.Date;

public class User {
	
	private String name;
	private String surname;
	private Date dateofBirth;
	private String sex;
	private Date subsriptionDate;
	private String userName;
	private String email;
	private String password;
	private String userType;
	
	
	
	public User() {
		
	}
	


	public User(String name, String surname, Date dateofBirth, String sex, Date subsriptionDate, String userName,
			String email, String password, String userType, String requestState, String inviteState) {
		super();
		this.name = name;
		this.surname = surname;
		this.dateofBirth = dateofBirth;
		this.sex = sex;
		this.subsriptionDate = subsriptionDate;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.userType = userType;
	}



	public void setName(String name) {
		this.name = name;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public void setDateofBirth(Date dateofBirth) {
		this.dateofBirth = dateofBirth;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public void setSubsriptionDate(Date subsriptionDate) {
		this.subsriptionDate = subsriptionDate;
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


	public Date getDateofBirth() {
		return dateofBirth;
	}


	public String getSex() {
		return sex;
	}


	public Date getSubsriptionDate() {
		return subsriptionDate;
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
	

}
