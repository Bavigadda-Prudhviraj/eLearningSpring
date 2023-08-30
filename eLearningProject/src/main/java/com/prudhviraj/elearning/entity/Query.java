package com.prudhviraj.elearning.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;





@Entity
@Table(name = "query")
public class Query {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "query_id")
	@Id
	int queryId;
	
//	@NotEmpty(message = "I'm sorrry but student name can't be left blank")
	@Column(name = "question",length = 250)
	String querys;
	
//	@NotEmpty(message = "I'm sorrry but student name can't be left blank")
	@Column(name = "answer",length = 250)
	String answer;
	
	
	//one student can rise many queries
	//many queries can answered by one faculty 
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_details")
	 Student student;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="faculty_details")
	
	 Faculty faculty;
	public Query() {
		super();
	}
	public Query(int queryId, String querys, String answer, Student student, Faculty faculty) {
		super();
		this.queryId = queryId;
		this.querys = querys;
		this.answer = answer;
		this.student = student;
		this.faculty = faculty;
	}
	public int getQueryId() {
		return queryId;
	}
	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}
	public String getQuerys() {
		return querys;
	}
	public void setQuerys(String querys) {
		this.querys = querys;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Faculty getFaculty() {
		return faculty;
	}
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	@Override
	public String toString() {
		return "Query [queryId=" + queryId + ", querys=" + querys + ", answer=" + answer + ", student=" + student
				+ ", faculty=" + faculty + "]";
	}

}
