package com.prudhviraj.elearning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="payment")
public class Payment{
//	@NotEmpty(message = "I'm sorrry but name can't be left blank")
	@Column(name = "name_on_card",length = 25)
	String nameOnTheCard;
	
//	@NotEmpty(message = "I'm sorrry but card number can't be left blank")
	@Column(name="card_number")
	@Id
	long cardNumber;
	
//	@NotEmpty(message = "I'm sorrry but cvv can't be left blank")
	@Column(name="cvv")
	int cvv;
	
//	@NotEmpty(message = "I'm sorrry but expire date can't be left blank")
	
//	@Pattern(regexp = "^(0[1-9]|1[0-2])\\/?([0-9]{4}|[0-9]{2})$")
	@Column(name="expire_date")
	String expireDate;
	
	
	//one to one relationship with student
	//one to one relationship with faculty
	@OneToOne
	Student student;
	@OneToOne
	Course course;
	public Payment() {
	super();
}
	public Payment(String nameOnTheCard, long cardNumber, int cvv, String expireDate, Student student, Course course) {
	super();
	this.nameOnTheCard = nameOnTheCard;
	this.cardNumber = cardNumber;
	this.cvv = cvv;
	this.expireDate = expireDate;
	//this.student = student;
	//this.course = course;
}
	public String getNameOnTheCard() {
		return nameOnTheCard;
	}
	public void setNameOnTheCard(String nameOnTheCard) {
		this.nameOnTheCard = nameOnTheCard;
	}
	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
//	public Student getStudent() {
//		return student;
//	}
//	public void setStudent(Student student) {
//		this.student = student;
//	}
//	public Course getCourse() {
//		return course;
//	}
//	public void setCourse(Course course) {
//		this.course = course;
//	}
	@Override
	public String toString() {
		return "Payment [nameOnTheCard=" + nameOnTheCard + ", cardNumber=" + cardNumber + ", cvv=" + cvv
				+ ", expireDate=" + expireDate + "]";
	}
	
}
