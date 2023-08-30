package com.prudhviraj.elearning.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prudhviraj.elearning.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
	
	
	 //returns the course based on the courseName
	 List<Course> findBycourseName(String courseName);
	 //returns the list of courses based on both parameters but we have to give one correct value
	 List<Course> findByCourseNameOrCoursePrice(String courseName,Integer coursePrice); //Capital letters for && and || methods
	 //returns the list of courses both should match
	 List<Course> findByCourseNameAndCoursePrice(String courseName,Integer coursePrice);
	 // Return the course whose price is greater than given price as method parameter
	 List<Course> findBycoursePriceGreaterThan(Integer coursePrice) ;
	 // Return the course whose rating is greater than given course rating as method parameter
	 List<Course> findBycourseRatingGreaterThan(Integer courseRating) ;
	 //Return the course whose price is Less than given price as method parameter
	 List<Course> findBycoursePriceLessThan(Integer courseRating) ;
	 //Return the filtered the product records that match the given text
	 List<Course> findBycourseNameContaining(String text) ;
	 //return the sorted array list based on the course name
	 List<Course> findBycourseNameContaining(String text,Sort sort) ;
	 //return the list of courses between mentioned prices
	 List<Course> findByCoursePriceBetween(Integer courseMinPrice,Integer courseMaxPrice) ;
	 //Returns List of courses based on multiple values
	 List<Course> findBycourseNameIn(List<String> courseNames);
	 
	 
}
