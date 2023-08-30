package com.prudhviraj.elearning.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;




@Entity
@Table(name="Admin")
public class Admin {
	@Email
	//@NotEmpty(message = "I'm sorrry but E-mail can't be left blank")
	@Column(name = "admin_email",length = 20)
	@Id
	String emailId;
	
	
	//@NotEmpty(message = "sorry Password cannot be blank")
	//@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message = "you should match your password to the above patern")
	@Column(name = "password",length =15 )
	String password;
	
	// admin can add as many as courses you want
	@OneToMany // non reference to one to many relationship
	List<Course> courses=new ArrayList<>();

	public Admin(String emailId, String password, ArrayList<Course> courses) {
		super();
		this.emailId = emailId;
		this.password = password;
		this.courses = courses;
	}
	public Admin() {
		super();
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	@Override
	public String toString() {
		return "Admin [emailId=" + emailId + ", password=" + password + ", courses=" + courses + "]";
	}

	

	

	
	
	
	
	
	
}
