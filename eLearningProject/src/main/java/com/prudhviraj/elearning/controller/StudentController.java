package com.prudhviraj.elearning.controller;


import java.util.List;
import java.util.Optional;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.prudhviraj.elearning.entity.Course;
import com.prudhviraj.elearning.entity.Student;
import com.prudhviraj.elearning.exception.CourseException;
import com.prudhviraj.elearning.exception.StudentException;
import com.prudhviraj.elearning.repository.CourseRepository;
import com.prudhviraj.elearning.repository.StudentRepository;
import com.prudhviraj.elearning.service.AdminService;
import com.prudhviraj.elearning.service.StudentService;



@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	StudentService studentService;
	@Autowired
	AdminService adminService;
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	CourseRepository courseRepository;
	Log studetLog=LogFactory.getLog(StudentController.class);
	
	@PostMapping("/studentRegistration")
	public ResponseEntity<?> studentRegistration(@RequestBody Student student) throws StudentException {
		return new ResponseEntity<>(studentService.studentRegistration(student),HttpStatus.OK);
	}
	
	//static data
	@PostMapping("/registerAsStudent")
	public ResponseEntity<?> registerAsStudent(Student student) throws Exception {
		return new ResponseEntity<>(studentService.studentRegistration(student),HttpStatus.OK);
		
		
		
	}
	//working
	@GetMapping("/studentLogin")
	public ResponseEntity<?> studentLogin(@RequestParam String studentEmailId,@RequestParam String studnetPassword) throws Exception{
		return new ResponseEntity<>(studentService.studentLogin(studentEmailId, studnetPassword), HttpStatus.OK);
	}
	//working
	@GetMapping("/viewStudentProfile")
	public ResponseEntity<?> searchUserByName(@RequestParam String studentEmailId) throws Exception {
		return new ResponseEntity<>(studentService.findStudentByEmail(studentEmailId), HttpStatus.FOUND); 
	}
	@PutMapping("/updateStudentDetails")
	public ResponseEntity<?> student(@RequestBody Student student) throws StudentException {
		return new ResponseEntity<>(studentService.updateStudentDetails(student),HttpStatus.FOUND);
	}
	@PostMapping("/addQuery")
	public ResponseEntity<?> addQuery(@RequestParam String query,@RequestParam String studentEmail) throws Exception {
		return new ResponseEntity<>(studentService.addQuery(query,studentEmail), HttpStatus.OK);
	}
	@PutMapping("/updateQuery")
	public ResponseEntity<?> updateQuery(@RequestParam Integer queryId,@RequestParam String query) throws QueryException {
		return new ResponseEntity<>(studentService.updateQuery(queryId,query), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteQueryById")
	public ResponseEntity<?> deleteQueryById(@RequestParam Integer queryId) {
		if (studentService.deleteQuery(queryId)) {
			studetLog.info("query is deleted succesfully");
			return new ResponseEntity<>("query is deleted "+queryId,HttpStatus.OK);
			
		}studetLog.warn("student tries to delete the query whci doesnt exist");
		return new ResponseEntity<>("query does not exit with "+queryId+" this id \nPlease check your Query Id",HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/allStudents")
	public List<Student> allStudents(){
		List<Student> students= studentService.allStudents();
		return students;
	}
	@GetMapping("/allCourses")
	public ResponseEntity<?> allCourses()  {
		return new ResponseEntity<>(studentService.viewAllCourses(), HttpStatus.OK);
		
	}
	
	
	@PutMapping("/StudentBuyingCourses")
	public ResponseEntity<?> StudentBuyingCourses(@RequestBody Student student) throws StudentException {
		return new ResponseEntity<>(studentService.studentBuyingCourses(student),HttpStatus.OK);
	}
	
	
	//working with error do touch
	@GetMapping("/findCourseByName")
	public ResponseEntity<?> courseByName(@RequestParam String courseName) throws CourseException {
		return new ResponseEntity<>(studentService.findCourseByName(courseName),HttpStatus.FOUND);
	}
	@GetMapping("/findByCourseNameOrCoursePrice")
	public ResponseEntity<?> findByCourseNameOrCoursePrice(@RequestParam String courseName, @RequestParam Integer coursePrice) throws CourseException {
	
		return new ResponseEntity<>(studentService.findCourseByNameOrPrice(courseName,coursePrice),HttpStatus.FOUND);
	}
	@GetMapping("/findByCourseNameAndCoursePrice")
	public ResponseEntity<?> findByCourseNameAndCoursePrice(@RequestParam String courseName, @RequestParam Integer coursePrice) throws CourseException {
		
		if (!"".equals(courseName)) {
			if (coursePrice>0) {
				return new ResponseEntity<>(studentService.findCourseByNameOrPrice(courseName,coursePrice),HttpStatus.FOUND);
			}throw new CourseException("coursePrice must be greather than 0");
			
		}throw new CourseException("course name cant be blank please please enter course name");
		
		
	}
	@GetMapping("/findBycoursePriceGreaterThan")
	public ResponseEntity<?> findBycoursePriceGreaterThan(@RequestParam Integer coursePriceGreaterThan) throws CourseException {
		return new ResponseEntity<>(studentService.findBycoursePriceGreaterThan(coursePriceGreaterThan),HttpStatus.FOUND);
			
		
		
	}
	@GetMapping("/findBycourseRatingGreaterThan")
	public ResponseEntity<?> findBycourseRatingGreaterThan(@RequestParam Integer courseRatingGreaterThan) throws CourseException {
		return new ResponseEntity<>(studentService.findBycourseRatingGreaterThan(courseRatingGreaterThan),HttpStatus.FOUND);
			
		
		
	}
	@GetMapping("/findBycoursePriceLessThan")
	public ResponseEntity<?> findBycourseRatingLessThan(@RequestParam Integer coursePriceLessThan) throws CourseException {
		return new ResponseEntity<>(studentService.findBycoursePriceLessThan(coursePriceLessThan),HttpStatus.FOUND);
			
		
		
	}
	@GetMapping("/findBycourseNameContaining")
	public ResponseEntity<?> findBycourseNameContaining(@RequestParam String containingText) throws CourseException {
		return new ResponseEntity<>(studentService.findBycourseNameContaining(containingText),HttpStatus.FOUND);
	}
	@GetMapping("/pagination")
	public ResponseEntity<?> pagination(@RequestParam Integer pageNumber,@RequestParam Integer pageSize) {
		return new ResponseEntity<>(studentService.pagination(pageNumber, pageSize), HttpStatus.FOUND);
	}
	@GetMapping("/sortingByCourseRating")
	public ResponseEntity<?> sorting(@RequestParam String courseName) throws CourseException {
		return new ResponseEntity<>(studentService.sortByCourseRating(courseName),HttpStatus.FOUND);
	}
	@GetMapping("/paginationAndSorting")
	public ResponseEntity<?> paginationAndSorting(@RequestParam Integer pageNumber,@RequestParam Integer pageSize) {
		return new ResponseEntity<>(studentService.paginationAndSorting(pageNumber, pageSize), HttpStatus.FOUND);
	}
	@GetMapping("/findBycoursePriceBetween")
	public ResponseEntity<?> findBycoursePriceBetween(@RequestParam Integer courseMinPrice,@RequestParam Integer courseMaxPrice) throws CourseException {
		List<Course> courses=courseRepository.findByCoursePriceBetween(courseMinPrice, courseMaxPrice);
		int count=0;
		for(@SuppressWarnings("unused") Object element : courses) {
			count=count+1;
		}
		if (courseMaxPrice<=courseMinPrice) {
			return new ResponseEntity<>("course max rpice should be less than then min price",HttpStatus.BAD_REQUEST);
		}
		if (count<1) {
			throw new CourseException("any course doesnt exist with given text");
		}
		
		return new ResponseEntity<>(courses,HttpStatus.FOUND);
	}
	@GetMapping("/findBycourseNameIn")
	public ResponseEntity<?> findBycourseNameIn(@RequestParam String course1 ,@RequestParam String course2) throws CourseException {
	
		return new ResponseEntity<>(studentService.findBycourseNameIn(List.of(course1,course2)),HttpStatus.FOUND);
	}
	

	@GetMapping("/viewCourseById")
	@Produces(MediaType.APPLICATION_JSON)
	public Optional<Course> getCourseById(@RequestParam Integer courseId) throws CourseException {
			return studentService.getCourseById(courseId);
	}
	@PutMapping("/updateCourseRating")
	public ResponseEntity<?> updateCourseRating(@RequestParam Integer queryId,@RequestParam Integer courseRating) throws  CourseException {
		return new ResponseEntity<>(studentService.courserRating(queryId,courseRating), HttpStatus.OK);
	}
}

