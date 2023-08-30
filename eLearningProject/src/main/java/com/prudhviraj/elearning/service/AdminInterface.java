package com.prudhviraj.elearning.service;

import java.util.List;

import com.prudhviraj.elearning.entity.Admin;
import com.prudhviraj.elearning.entity.Course;
import com.prudhviraj.elearning.entity.Faculty;
import com.prudhviraj.elearning.entity.Student;
import com.prudhviraj.elearning.exception.AdminException;

public interface AdminInterface {
	public boolean adminRegistration(Admin admin);
	public boolean UpdatePassword(Admin admin);
	public Admin adminLogin(String adminEmailId, String adminPassword) throws AdminException;
	public boolean addMultipleCourse(Admin admin);
	public boolean deleteCourseById(int id);
	public Course updateCourses(Course course);
	public Course searchCourseById(int CourseId) throws Exception;
	public boolean deleteFaculty(String facultyEmail);
	public List<Course> viewAllCourses();
	public List<Student> viewAllStudents() ;
	public List<Faculty> viewAllfacultyList();

}
