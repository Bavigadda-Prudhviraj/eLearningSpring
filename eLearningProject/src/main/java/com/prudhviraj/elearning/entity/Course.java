package com.prudhviraj.elearning.entity;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="course")
public class Course {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	@Id
	int courseId;
	
	//@NotEmpty(message = "I'm sorrry but course name can't be left blank")
	@Column(name = "name",length = 25)
	String courseName;
	
	//@NotEmpty(message = "I'm sorrry but course price can't be left blank")
	@Column(name = "price")
	int coursePrice;
	
	@Column(name="course_rating")
	//@Size(max = 5,min = 1)
	int courseRating;
	

	public int getCourseRating() {
		return courseRating;
	}

	public void setCourseRating(int courseRating) {
		this.courseRating = courseRating;
	}

	// relations
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "courseSet")//courseSet in course entity
	@JsonIgnore
	List<Student> studentSet=new ArrayList<>();
	
	// relations 
	//many students can buy one courses and one course can buy many students @manytomany 
	
	public Course() {
		
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(int coursePrice) {
		this.coursePrice = coursePrice;
	}

	public List<Student> getStudentSet() {
		return studentSet;
	}

	public void setStudentSet(List<Student> studentSet) {
		this.studentSet = studentSet;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", coursePrice=" + coursePrice
				+ ", courseRating=" + courseRating + ", studentSet=" + studentSet + "]";
	}

	public Course(int courseId, String courseName, int coursePrice, @Size(max = 5, min = 1) int courseRating,
			List<Student> studentSet) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.coursePrice = coursePrice;
		this.courseRating = courseRating;
		this.studentSet = studentSet;
	}

	
	

}
