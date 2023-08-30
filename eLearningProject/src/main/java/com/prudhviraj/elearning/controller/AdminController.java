package com.prudhviraj.elearning.controller;




import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.prudhviraj.elearning.entity.Admin;
import com.prudhviraj.elearning.entity.Course;
import com.prudhviraj.elearning.entity.Faculty;
import com.prudhviraj.elearning.entity.Student;
import com.prudhviraj.elearning.repository.AdminRepository;
import com.prudhviraj.elearning.repository.CourseRepository;
import com.prudhviraj.elearning.repository.FacultyRepository;
import com.prudhviraj.elearning.repository.StudentRepository;
import com.prudhviraj.elearning.service.AdminService;



@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService adminService;
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	AdminRepository adminRepo;
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	FacultyRepository facultyRepo;
	
	
	Log log= LogFactory.getLog(AdminController.class);
	
	@PostMapping("/adminRegistration")
	public ResponseEntity<?> registrationAsAdmin(@RequestBody Admin admin) throws Exception{
		if(adminService.adminRegistration(admin)) {
			log.info("[AdminController] - Logging in was done by the administrator");
			return new ResponseEntity<>("Your registration as a admin was successful", HttpStatus.OK);
		}
		log.error("[AdminController] - Logging in with incorrect credentials was attempted by the administrator");
		return new ResponseEntity<>("Your registration as a admin was unsuccessful\nBeacause are are already enrolled with this email as admin",HttpStatus.BAD_REQUEST);
		
	}
	
	@GetMapping("/adminLogin")
	public ResponseEntity<?> loginUserStatic(@RequestParam String adminEmail,@RequestParam String password) throws Exception{
		log.info("[AdminController] - The admin logged in");
		
		return new ResponseEntity<>(adminService.adminLogin(adminEmail, password), HttpStatus.OK);
		
	}
	
	@GetMapping("/adminLoginss")
	public ResponseEntity<?> loginUser(@RequestBody Admin admin) throws Exception{
		String adminEmailId=admin.getEmailId();
		String adminPassword =admin.getPassword();
		return new ResponseEntity<>(adminService.adminLogin(adminEmailId, adminPassword), HttpStatus.OK);
	}
	
	@PutMapping("/addMultipleCourses")
	public ResponseEntity<?> addMultipleCourses(@RequestBody Admin admin) {
		if (adminService.addMultipleCourse(admin)) {
			log.info("[AdminController] - More than one course was added by the administrator");
			return new ResponseEntity<>("courses area added successfully",HttpStatus.OK);
		}
		log.error("[AdminController] - the case of multiple courses, the failed to add them administrator");
		return new ResponseEntity<>("courses adding is unsuccessfully\nplease check your email once",HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/updateCourse")
	public ResponseEntity<?> updateCourse(@RequestBody Course course) throws Exception{
		int courseId=course.getCourseId();
		if (courseRepo.existsById(courseId)) {
			log.info("[AdminController] - Course details were updated with the course ID "+courseId+" by Course the administrator");
			return new ResponseEntity<>(adminService.updateCourses(course),HttpStatus.OK);
		}
		log.error("[AdminController] - An administrator attempted to update course details with an invalid course ID:: "+courseId);
		return new ResponseEntity<>("It appears that the course with the given ID"+courseId +"does not exist",HttpStatus.OK);
	}
	
	@PutMapping("/updateAdminPassword")
	public ResponseEntity<?> updateAdminPassword(@RequestBody Admin admin) {
		String adminEmailId=admin.getEmailId();
		if (adminRepo.existsById(adminEmailId)) {
			adminService.UpdatePassword(admin);
//			Class<AdminController> controllerClass = AdminController.class;
//			Link selfLink = linkTo(methodOn(controllerClass).all()).withSelfRel().andAffordance(afford(methodOn(controllerClass).newEmployee(null))); // <2>
			log.info("[AdminController] - The administrator updated his profile information");
			return new ResponseEntity<>("The password you entered has been successfully updated, admin ",HttpStatus.OK);
		}
		log.error("[AdminController] - The administrator attempted to update his details with the wrong email address");
		return new ResponseEntity<>("It appears that the admin with the given "+adminEmailId +" E-mail does not exist",HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCourse")
	public ResponseEntity<?> deleteCourse(@RequestParam int courseId)throws Exception {
		if(courseRepo.existsById(courseId)) {
			adminService.deleteCourseById(courseId);
			log.info("[AdminController] - There is a course with the following ID "+courseId+ " that has been deleted by admin ");
			return new ResponseEntity<>("course is deleted succesfully",HttpStatus.FOUND);
		}
		log.error("[AdminController] - Courses with a course id "+courseId+" that does not exist were attempted to be deleted by the administrator");
		return new ResponseEntity<>("It appears that the course with the given ID "+ courseId +" does not exist",HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping("/deleteFaculty")
	public ResponseEntity<?> deleteQueryById(@RequestParam String facultyEmail) {
		if (facultyRepo.existsById(facultyEmail)) {
			adminService.deleteFaculty(facultyEmail);
			log.info("[AdminController] - A faculty member with this"+facultyEmail + " email address has been deleted by admin");
			return new ResponseEntity<>("faculty is deleted whose Email "+facultyEmail,HttpStatus.OK);
				}
		log.error("[AdminController] - An administrator attempted to delete a faculty member with an invalid email address::"+ facultyEmail);
		return new ResponseEntity<>("faculty doesnt exist with "+facultyEmail+" this Email\nPlease check the faculty Email once",HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/viewAllCourses")
	public List<Course> viewAllCourses(){
		List<Course> courses= adminService.viewAllCourses();
		log.info("[AdminController] - All Course details were viewed by admin");
		return courses;
	}
	
	@GetMapping("/searchForCourseByCourseId")
	public ResponseEntity<?> searchForCourseByCourseId(@RequestParam Integer courseId)throws Exception {
		if (courseRepo.existsById(courseId)) {
			log.info("[AdminController] - The admin viewed the course details with the course ID:: "+ courseId);
			return new ResponseEntity<>(adminService.searchCourseById(courseId),HttpStatus.FOUND);
			
		}
		log.error("[AdminController] - The administrator searched for a course with courseId::"+ courseId+" that didn't exist");
		return new ResponseEntity<>("It appears that the course with the given ID "+ courseId +" does not exist",HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/viewAllStudents")
	public List<Student> viewAllStudents() {
		log.info("[AdminController] - all faculty profiles  was viewed by the A list of admin");
		return adminService.viewAllStudents();
	}
	
	@GetMapping("/viewAllfaculty")
	public List<Faculty> viewAllfacultyList() {
		log.info("[AdminController] - all student profiles  was viewed by the A list of admin");
		return adminService.viewAllfacultyList();
				}
}
