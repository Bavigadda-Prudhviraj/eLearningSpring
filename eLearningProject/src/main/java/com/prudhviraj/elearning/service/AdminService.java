package com.prudhviraj.elearning.service;



import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prudhviraj.elearning.entity.Admin;
import com.prudhviraj.elearning.entity.Course;
import com.prudhviraj.elearning.entity.Faculty;
import com.prudhviraj.elearning.entity.Student;
import com.prudhviraj.elearning.exception.AdminException;
import com.prudhviraj.elearning.exception.CourseException;
import com.prudhviraj.elearning.repository.AdminRepository;
import com.prudhviraj.elearning.repository.CourseRepository;
import com.prudhviraj.elearning.repository.FacultyRepository;
import com.prudhviraj.elearning.repository.StudentRepository;



@Service
public class AdminService implements AdminInterface{
	@Autowired
	AdminRepository adminRepo;
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	FacultyRepository facultyRepo;
	
	Log logg= LogFactory.getLog(AdminService.class);
	

	public boolean adminRegistration(Admin admin) {
		String adminEmail=admin.getEmailId();
		if (adminRepo.existsById(adminEmail)) {
			return false;
		}
			adminRepo.save(admin);
			return true;
		}
	public boolean UpdatePassword(Admin admin) {
		adminRepo.save(admin);
		return true;
	}
	public Admin adminLogin(String adminEmailId, String adminPassword) throws AdminException {
		if(adminRepo.existsById(adminEmailId)) {
			Admin user=adminRepo.findById(adminEmailId).get();
			
			
			if(adminPassword.equals(user.getPassword())) {
				//user.add(linkTo(methodOn(AdminController.class).getOne(user.getEmailId())));
				
				return user;
			}
			else {
				logg.error("[AdminService] - A wrong password was used by the administrator to log in");
				throw new AdminException("Sorry your password is incorrect, please give correct password");
			}
		}
		else {
			logg.error("[AdminService] - A wrong email address was used by the administrator to log in");
			throw new AdminException("Username not found with "+adminEmailId +"E-mail" );
		}
		
	}
	public boolean addMultipleCourse(Admin admin) {
		if (adminRepo.existsById(admin.getEmailId())) {
			List<Course> courses=admin.getCourses();
			courseRepo.saveAll(courses);
			adminRepo.save(admin);
			return true;
		}
		else {
			return false;
		}
	}
	public boolean deleteCourseById(int id) {
		if (courseRepo.existsById(id)) {
			courseRepo.deleteById(id);
			return true;
		}else {
			return false;
		}
	}
	public Course updateCourses(Course course) {
		 courseRepo.save(course);
		 return course;
	}
	public Course searchCourseById(int CourseId) throws Exception {
		if(courseRepo.existsById(CourseId)) {
			return courseRepo.findById(CourseId).get();
		}
		else {
			throw new CourseException("course Not found with course Id :: "+ CourseId);
		}
	}
	public boolean deleteFaculty(String facultyEmail) {
		facultyRepo.deleteById(facultyEmail);
		return true;
		
	}
	public List<Course> viewAllCourses() {
		List<Course> courses= courseRepo.findAll();
		return courses;
	}	
	public List<Student> viewAllStudents() {
		return studentRepo.findAll();
	}
	public List<Faculty> viewAllfacultyList() {
		return facultyRepo.findAll();
	}
	
}
