package com.prudhviraj.elearning.entity;
//package com.virtusa.elearning.entity;
//
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Pattern;
//@Entity
//@Table(name="registration")
//public class Registration {
//	
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
//	int id;
//	
//	@Column(name = "role",length = 8)
//	@NotEmpty(message = "I'm sorrry but role can't be left blank")
//	String role;
//	
//	@NotEmpty(message = "I'm sorrry but name can't be left blank")
//	@Column(name = "name",length = 25)
//	String name;
//	
//	@NotEmpty(message = "I'm sorrry but phone number can't be left blank")
//	@Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$",message = "The mobile number you entered should be the appropriate one")
//	@Column(name = "phone_number")
//	long phoneNumber;
//	
//	@Email
//	@NotEmpty(message = "I'm sorrry but E-mail can't be left blank")
//	@Column(name = "email",length = 20)
//	@Id
//	String email;
//	
//	@NotEmpty(message = "sorry Password cannot be blank")
//	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message = "you should match your password to the above patern")
//	@Column(name = "password",length =15 )
//	String  password;
//
//	
//	
//	public Registration() {
//	super();
//	}
//
//	public Registration(int id, String role, String name, long phoneNumber, String email, String password) {
//	super();
//	this.id = id;
//	this.role = role;
//	this.name = name;
//	this.phoneNumber = phoneNumber;
//	this.email = email;
//	this.password = password;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public long getPhoneNumber() {
//		return phoneNumber;
//	}
//
//	public void setPhoneNumber(long phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	@Override
//	public String toString() {
//		return "Registration [id=" + id + ", role=" + role + ", name=" + name + ", phoneNumber=" + phoneNumber
//				+ ", email=" + email + ", password=" + password + "]";
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//
//}
