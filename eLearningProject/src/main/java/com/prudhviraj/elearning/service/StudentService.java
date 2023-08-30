package com.prudhviraj.elearning.service;



import java.util.List;
import java.util.Optional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.prudhviraj.elearning.entity.Course;
import com.prudhviraj.elearning.entity.Payment;
import com.prudhviraj.elearning.entity.Query;
import com.prudhviraj.elearning.entity.Student;
import com.prudhviraj.elearning.exception.CourseException;
import com.prudhviraj.elearning.exception.StudentException;
import com.prudhviraj.elearning.repository.CourseRepository;
import com.prudhviraj.elearning.repository.FacultyRepository;
import com.prudhviraj.elearning.repository.PaymentRepository;
import com.prudhviraj.elearning.repository.QueryRepository;
import com.prudhviraj.elearning.repository.StudentRepository;




@Service
public class StudentService implements StudentInterface {
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	QueryRepository queryRepos;
	@Autowired
	PaymentRepository paymentRepo;
	@Autowired
	FacultyRepository facultyRepository;
	

	Log studetLog=LogFactory.getLog(StudentService.class);
	
	public String studentRegistration(Student student) throws StudentException {
		if (studentRepo.existsById(student.getStudentEmail())) {
			studetLog.error("Using the same email address as before, the student tried to register");
			throw new StudentException("Hello, student! The email you provided is already associated with your student account,please log in");
		}
		else {
			studetLog.info("This "+student.getStudentEmail()+" email address has been successfully registered as a student for you");
			 studentRepo.save(student);
			 return "Registration as a student has been completed successfully.";
		}
		
		
	}
	public ResponseEntity<?> studentBuyingCourses(Student student) throws StudentException {
		List<Course> courses=student.getCourseSet();
		
		for (Course course : courses) {
			if (studentRepo.existsById(student.getStudentEmail())) {
				if (courseRepo.existsById(course.getCourseId())) {
					paymentRepo.save(student.getPayment());
					student.setCourseSet(courses);
					studentRepo.save(student);
					studetLog.info(" student with "+student.getStudentEmail() +" successfully you purchased your courses succesfully ");
					return new ResponseEntity<> ("succesfully you purchased your courses succesfully",HttpStatus.OK);
					
			}studetLog.debug("Trying to buy the courses which don't exist in the courses");
				throw new StudentException("Your,Trying to buy the courses which don't exist in the courses");
			}
			studetLog.error("Trying to buy the courses,without registration as student");
			throw new StudentException("Your email address is not listed in our database. You need to register as a student in order to purchase the courses.");
		}
		return new ResponseEntity<> ("succesfully you purchased your courses successfully",HttpStatus.OK);
		
	} 
	
	
	
	
	
	
	public String studentLogin(String username, String password) throws Exception {
		if(studentRepo.existsById(username)) {
			Student user=studentRepo.findById(username).get();
			if(password.equals(user.getStudentPassword())) {
				studetLog.info("Students using this "+ username +" email address logged in successfully");
				return "Hello student, you have successfully logged in to your account with this "+username+" email address";
			}
			else{
				studetLog.debug("The student with this "+username+" email tries to log in with the wrong password.");
				throw new StudentException("Sorry your password is incorrect, please give correct password");
			}
		}
			else{
				studetLog.error("Your email address is not listed in our database. You need to register as a student in order to login as student.");
			throw new StudentException("this "+username+"  email address is not listed in our database. You need to register as a student in order to login as student" );
		}

		
	}	
	public Student findStudentByEmail(String studentEmail)throws Exception {
		if(studentRepo.existsById(studentEmail)) {
			return studentRepo.findById(studentEmail).get();
		}
		else {
			throw new CourseException("student Not exist with student Email :: "+ studentEmail);
		}
	}
	public boolean deleteQuery(int queryId) {
		if(queryRepos.existsById(queryId)) {
			queryRepos.deleteById(queryId);
			return true;
		}else {
			return false;
		}
	}
	public List<Course> viewAllCourses() {
		return courseRepo.findAll();
	}
	
	
	public String updateStudentDetails(Student student)throws StudentException {
		String studentEmailId=student.getStudentEmail();
		if (studentRepo.existsById(studentEmailId)) {
			List<Query> queries=student.getQueryList();
			queryRepos.saveAll(queries);
			studetLog.info("His profile was successfully updated with this "+studentEmailId+" email address");
			 studentRepo.save(student);
			 return "The details of your account have been updated successfully";
		}
		studetLog.warn("When updating his details, a student uses the incorrect email address");
		throw new StudentException("In order to update your details, make sure your email address is registered as a student first");
		
	}
	public boolean existsById(int id) {
		courseRepo.existsById(id);
		return true;
	}
	public String updateQuery(Integer queryId,String question)throws QueryException {
		if (queryRepos.existsById(queryId)) {
			Optional<Query> queryOptional=queryRepos.findById(queryId);
			Query query=queryOptional.get();
			query.setQuerys(question);
			queryRepos.save(query);
			studetLog.info("query is updated succesfully whose id:: "+queryId);
			return "query is updated succesfully";
		}
		studetLog.warn("query is updated unsuccesfully whose id:: "+queryId);
		throw new QueryException("We are unable to match the query ID provided with the quers in database.");
		
	}
	public Query addQuery(String query,String studentEmail) throws Exception {
		Query query2=new Query();
		query2.setQuerys(query);
		
		if (studentRepo.existsById(studentEmail)) {
			Optional<Student> studentInfo=studentRepo.findById(studentEmail);
			Student student=studentInfo.get();
			query2.setStudent(student);
			studentRepo.save(student);
			studetLog.info("Query is added succesfully");
		  return queryRepos.save(query2);
		}else {
			throw new QueryException("student please check the student email address");
			
		}
		
		
}	
	public Payment addPaymentDetails(Payment payment) {
		return paymentRepo.save(payment);
		
	}
	public List<Student> allStudents() {
		 return studentRepo.findAll();
		
	}
	public List<Course> findCourseByName( String courseName) throws CourseException{
		List<Course> course=courseRepo.findBycourseName(courseName);
		int count = 0;
		for (@SuppressWarnings("unused") Object element : course) {
			
			count=count+1;
		}
		if (count<1) {
			throw new CourseException("course doesnt exist with gicen name please check name of ghe course");
		}
		return course;
		
		
	}
	public List<Course> findCourseByNameOrPrice(String courseName,int coursePrice) throws CourseException {
		List<Course> courses=courseRepo.findByCourseNameOrCoursePrice(courseName, coursePrice);
		int count=0;
		for(@SuppressWarnings("unused") Object element : courses) {
			count=count+1;
		}
		if (count<1) {
			throw new CourseException("course doesnt exist with gicen name please check name of ghe course");
		}
		return courses;
		
	}
	public List<Course> findByCourseNameAndCoursePrice(String courseName,int coursePrice) throws CourseException {
		List<Course> courses=courseRepo.findByCourseNameAndCoursePrice(courseName, coursePrice);
		int count=0;
		for(@SuppressWarnings("unused") Object element : courses) {
			count=count+1;
		}
		if (count<1) {
			throw new CourseException("course doesnt exist with given price");
		}
		return courses;
		
	}
	public List<Course> findBycoursePriceGreaterThan(int coursePrice) throws CourseException {
		List<Course> courses=courseRepo.findBycoursePriceGreaterThan(coursePrice);
		int count=0;
		for(@SuppressWarnings("unused") Object element : courses) {
			count=count+1;
		}
		if (count<1) {
			throw new CourseException("course doesnt exist greaterthan the mention price");
		}
		return courses;
		
		
	}
	public List<Course> findBycourseRatingGreaterThan(int courseRating) throws CourseException {
		List<Course> courses=courseRepo.findBycourseRatingGreaterThan(courseRating);
		int count=0;
		for(@SuppressWarnings("unused") Object element : courses) {
			count=count+1;
		}
		if (count<1) {
			throw new CourseException("course doesnt exist greaterthan the mention course rating");
		}
		return courses;
		
		
	}
	public List<Course> findBycoursePriceLessThan(int coursePrice) throws CourseException {
		List<Course> courses=courseRepo.findBycoursePriceLessThan(coursePrice);
		int count=0;
		for(@SuppressWarnings("unused") Object element : courses) {
			count=count+1;
		}
		if (count<1) {
			throw new CourseException("course doesnt exist lessthan the price");
		}
		return courses;
		
		
	}
	public List<Course> findBycourseNameContaining(String text) throws CourseException {
		List<Course> courses=courseRepo.findBycourseNameContaining(text);
		int count=0;
		for(@SuppressWarnings("unused") Object element : courses) {
			count=count+1;
		}
		if (count<1) {
			throw new CourseException("any course doesnt exist with given text");
		}
		return courses;
		
	}
	public List<Course> pagination(int pageNumber,int pageSize) {
		Pageable pages=PageRequest.of(pageNumber, pageSize);
		return courseRepo.findAll(pages).getContent();
	}
	public List<Course> sortByCourseRating( String name) throws CourseException{
		Sort sort=Sort.by(Sort.Direction.DESC,"courseRating");
		return courseRepo.findBycourseNameContaining(name,sort);
	}
	public List<Course> paginationAndSorting(int pageNumber,int pageSize) {
		Pageable pages=PageRequest.of(pageNumber, pageSize,Direction.DESC,"courseRating");
		return courseRepo.findAll(pages).getContent();
	}
	public List<Course> findBycourseNameIn(List<String> courseNames) {
		return courseRepo.findBycourseNameIn(courseNames);
		
	}
	public String courserRating(Integer courseId,Integer courseRating)throws CourseException {
		if (courseRepo.existsById(courseId)) {
			if (courseRating>=1 && courseRating<=5) {
			Optional<Course> queryOptional=courseRepo.findById(courseId);
			Course query=queryOptional.get();
			query.setCourseRating(courseRating);
			courseRepo.save(query);
			studetLog.info("Course rating is updated succesfully whose id:: "+courseId);
			return "query is updated succesfully";
			}throw new CourseException("course rating must be in between 1 to 5 scale rating");
		}
		studetLog.warn("query is updated unsuccesfully whose id:: "+courseId);
		throw new CourseException("We are unable to match the query ID provided with the quers in database.");
		
	}
	@Override
	public Optional<Course> getCourseById(int id)throws CourseException {
			return courseRepo.findById(id);
	}
}
