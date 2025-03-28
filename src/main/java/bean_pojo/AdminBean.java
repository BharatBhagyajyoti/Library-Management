package bean_pojo;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class AdminBean implements Serializable {

	
	private String name;
	private String email;
	private String phone;
	private Date dob;
	private String password;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = Date.valueOf(dob);
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
