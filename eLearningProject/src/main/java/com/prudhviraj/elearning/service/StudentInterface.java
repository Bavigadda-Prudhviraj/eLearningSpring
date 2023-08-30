package com.prudhviraj.elearning.service;

import java.util.List;
import java.util.Optional;
import org.hibernate.QueryException;
import org.springframework.http.ResponseEntity;
import com.prudhviraj.elearning.entity.Course;
import com.prudhviraj.elearning.entity.Payment;
import com.prudhviraj.elearning.entity.Query;
import com.prudhviraj.elearning.entity.Student;
import com.prudhviraj.elearning.exception.CourseException;
import com.prudhviraj.elearning.exception.StudentException;

public interface StudentInterface {
	public String studentRegistration(Student student) throws StudentException;
	public ResponseEntity<?> studentBuyingCourses(Student student) throws StudentException;
	public String studentLogin(String username, String password) throws Exception;
	public Student findStudentByEmail(String studentEmail)throws Exception;
	public boolean deleteQuery(int queryId);
	public List<Course> viewAllCourses();
	public Optional<Course> getCourseById(int id) throws CourseException;
	public String updateStudentDetails(Student student)throws StudentException;
	public boolean existsById(int id);
	public String updateQuery(Integer queryId,String question)throws QueryException;
	public Query addQuery(String query,String studentEmail) throws Exception;
	public Payment addPaymentDetails(Payment payment);
	public List<Student> allStudents();
	public List<Course> findCourseByName( String courseName) throws CourseException;
	public List<Course> findCourseByNameOrPrice(String courseName,int coursePrice) throws CourseException ;
	public List<Course> findByCourseNameAndCoursePrice(String courseName,int coursePrice) throws CourseException;
	public List<Course> findBycoursePriceGreaterThan(int coursePrice) throws CourseException;
	public List<Course> findBycourseRatingGreaterThan(int courseRating) throws CourseException;
	public List<Course> findBycoursePriceLessThan(int coursePrice) throws CourseException;
	public List<Course> findBycourseNameContaining(String text) throws CourseException;
	public List<Course> pagination(int pageNumber,int pageSize);
	public List<Course> sortByCourseRating( String name) throws CourseException;
	public List<Course> paginationAndSorting(int pageNumber,int pageSize) ;
	public List<Course> findBycourseNameIn(List<String> courseNames);
	public String courserRating(Integer courseId,Integer courseRating)throws CourseException ;

}
