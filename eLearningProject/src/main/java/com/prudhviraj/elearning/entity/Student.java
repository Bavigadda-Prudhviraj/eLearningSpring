package com.prudhviraj.elearning.entity;



import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="student")
public class Student {

	
//	@NotEmpty(message = "I'm sorrry but student name can't be left blank")
	@Column(name = "name",length = 25)
	String studentName;
	
	@Email
//	@NotEmpty(message = "I'm sorrry but E-mail can't be left blank")
	@Column(name = "email",length = 30)
	@Id
	String studentEmail;
	
//	@NotEmpty(message = "sorry Password cannot be blank")
//	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message = "you should match your password to the above patern")
	@Column(name = "password",length =30 )
	String studentPassword;
	

	//relations
	//one student can buy many courses and one course can brought by many students
	//one student can ask many questions
	
	@ManyToMany(cascade = CascadeType.ALL)
	List<Course> courseSet=new ArrayList<>();
	
	
	
	@OneToMany(mappedBy = "student")
	@JsonIgnore//it must it forms loops while viewing the student data
	List<Query> queryList=new ArrayList<>(); 
	
	@OneToOne 
	Payment payment;
	
	public Student() {
		super();
	}

	public Student( String studentName, String studentEmail, String studentPassword,
			List<Course> courseSet,  List<Query> queryList, Payment payment) {
		super();
		
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.studentPassword = studentPassword;
		this.courseSet = courseSet;
		
		this.queryList = queryList;
		this.payment = payment;
	}

	

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	public List<Course> getCourseSet() {
		return courseSet;
	}

	public void setCourseSet(List<Course> courseSet) {
		this.courseSet = courseSet;
	}

	

	public List<Query> getQueryList() {
		return queryList;
	}

	public void setQueryList(List<Query> queryList) {
		this.queryList = queryList;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Student [studentName=" + studentName + ", studentEmail=" + studentEmail + ", studentPassword="
				+ studentPassword + ", courseSet=" + courseSet + ", queryList=" + queryList + ", payment=" + payment
				+ "]";
	}

	
	
	

}
