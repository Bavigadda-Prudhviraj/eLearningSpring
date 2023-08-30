package com.prudhviraj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.prudhviraj.elearning.entity.Admin;
import com.prudhviraj.elearning.entity.Course;
import com.prudhviraj.elearning.repository.AdminRepository;
import com.prudhviraj.elearning.repository.CourseRepository;
import com.prudhviraj.elearning.service.AdminService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ELearningApplicationTests {
	@Autowired
	AdminService adminService;
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	CourseRepository courseRepository;
	
	Log log= LogFactory.getLog(ELearningApplicationTests.class);
	
	@BeforeAll
    static void init() {
        System.out.println("<=====================all the test methods are testing started=====================>");
    }
	
	 @BeforeEach
	    void init(TestInfo testInfo) {
	        String callingTest = testInfo.getTestMethod().get().getName();
	        System.out.println(callingTest);
	    }
	
	
	
	
	
	@Test
	@Order(1)
	@RepeatedTest(value = 2)
	@DisplayName("all courses int the database")
	void getAllCourses() {
		log.info("[test class] - view all courses test cases");
		assertTrue(adminService.viewAllCourses().size()>0);
	}
	
	@Test
	@Order(2)
	@DisplayName("view all faculty test case")
	void viewAllfacultyList() {
		assertTrue(adminService.viewAllfacultyList().size()>0);
		
	}

	@Test
	@Order(3)
	@DisplayName("view all students test casd")
	void viewAllStudents() {
		assertTrue(adminService.viewAllStudents().size()>0);
	}
	
	@Test
	@Order(4)
	@DisplayName("search for course by couese ID")
	void searchCourseById() throws Exception {
		assertNotNull(adminService.searchCourseById(1));
		
	}
	
	@Test
	@Order(5)
	@DisplayName("admin updating password test case")
	void updatePassword() {
		Admin admin=new Admin();
		admin.setEmailId("junit@gmail.com");
		admin.setPassword("junit@123");
		adminService.UpdatePassword(admin);
		assertEquals("junit@123",adminRepository.findById("junit@gmail.com").get().getPassword());
		
	}
	
	@Test
	@Order(6)
	@DisplayName("admin login test Case")
	void adminLogin() throws Exception {
		assertNotNull(adminService.adminLogin("junit@gmail.com", "junit@123"));
		
	}
	
	@Test
	@Order(7)
	@DisplayName("updating a course ")
	void updateCourse() {
		Course course=new Course();
		course.setCourseId(1);
		course.setCourseName("jUnitTest");
		course.setCoursePrice(1499);
		adminService.updateCourses(course);
		assertEquals("jUnitTest",courseRepository.findById(1).get().getCourseName());
	}
	
	//note change the courses id after deleted or else test case will be failed or 
	//no need to change the course because is false statement give false if course does not exist in the data base
	@Test
	@Order(8)
	@DisplayName("deleting course by corse Id")
	void deleteCourseByCourseId() {
		adminService.deleteCourseById(40);
		assertThat(courseRepository.existsById(40)).isFalse();
	}
	 @AfterAll
	    static void after() {
	        System.out.println("<=====================all the test methods testing is completed=====================>");
	    }
	

}
