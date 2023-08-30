package com.prudhviraj.elearning.entity;

import java.util.ArrayList;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="faculty")
public class Faculty {
	
	
	//@NotEmpty(message = "I'm sorrry but name can't be left blank")
	@Column(name = "name",length = 25)
	String facultyName;
	
	@Email
	//@NotEmpty(message = "I'm sorrry but E-mail can't be left blank")
	@Id
	@Column(name = "email",length = 20)
	String facultyEmail;
	
	//@NotEmpty(message = "sorry Password cannot be blank")
	//@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message = "you should match your password to the above pattern")
	@Column(name = "password",length =15 )
	String facultyPassword;
	
	 //relations
	//one faculty can answer to many queries
	@OneToMany(cascade = CascadeType.ALL)// no mapped by in one to many relation
	@JsonIgnore
	List<Query> querySet=new ArrayList<>();
	
	public Faculty() {
		super();
	}
	public Faculty( String facultyName, String facultyEmail, String facultyPassword, 
			List<Query> querySet) {
		super();
		
		this.facultyName = facultyName;
		this.facultyEmail = facultyEmail;
		this.facultyPassword = facultyPassword;
		this.querySet = querySet;
	}
	
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	public String getFacultyEmail() {
		return facultyEmail;
	}
	public void setFacultyEmail(String facultyEmail) {
		this.facultyEmail = facultyEmail;
	}
	public String getFacultyPassword() {
		return facultyPassword;
	}
	public void setFacultyPassword(String facultyPassword) {
		this.facultyPassword = facultyPassword;
	}
	
	public List<Query> getQuerySet() {
		return querySet;
	}
	public void setQuerySet(List<Query> querySet) {
		this.querySet = querySet;
	}
	@Override
	public String toString() {
		return "Faculty [facultyName=" + facultyName + ", facultyEmail=" + facultyEmail + ", facultyPassword="
				+ facultyPassword + ", querySet=" + querySet + "]";
	}
	
}
