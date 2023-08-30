package com.prudhviraj.elearning.service;

import java.util.List;
import java.util.Optional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prudhviraj.elearning.controller.StudentController;
import com.prudhviraj.elearning.entity.Faculty;
import com.prudhviraj.elearning.entity.Query;
import com.prudhviraj.elearning.exception.FacultyException;
import com.prudhviraj.elearning.exception.QueryException;
import com.prudhviraj.elearning.repository.FacultyRepository;
import com.prudhviraj.elearning.repository.QueryRepository;

@Service
public class FacultyService  implements FacultyInterface{
	@Autowired
	QueryRepository queryRepo;
	@Autowired
	FacultyRepository facultyRepo;
	Log facultyLog=LogFactory.getLog(StudentController.class);
	//working
	public Faculty registerAsFaculty(Faculty faculty)throws Exception{
		String facultyPassword=faculty.getFacultyPassword();
		String facultyName=faculty.getFacultyName();
		String facultyEmailAddress=faculty.getFacultyEmail();
		if (facultyRepo.existsById(facultyEmailAddress)) {
			facultyLog.warn(facultyEmailAddress);
			throw new FacultyException("[FacultyService] - email address already registred as faculty before only please try to login");
		}
		else {
			if (facultyName!="") {
				if (facultyPassword!="") {
					facultyLog.info("[FacultyService] - The faculty registration process has been completed successfully");
					return facultyRepo.save(faculty);
				}facultyLog.warn("[FacultyService] - It is impossible to have a null  password");
				throw new FacultyException("It is impossible to have a null  password ");
				
			}facultyLog.warn("[FacultyService] - It is impossible to have a null name");
			throw new FacultyException("name cant be null");
		}
	}
	
	public Faculty facultyLogin(String facultyEmailID, String facultyPassword) throws Exception {
		if(facultyRepo.existsById(facultyEmailID)) {
			Faculty faculty=facultyRepo.findById(facultyEmailID).get();
			if(facultyPassword.equals(faculty.getFacultyPassword())) {
				facultyLog.info("[FacultyService] - The faculty logged in");
				return faculty;
			}
			else {
				facultyLog.warn("[FacultyService] - Sorry your password is incorrect, please give correct password");
				throw new FacultyException("Sorry your password is incorrect, please give correct password");
			}
		}
		else {
			facultyLog.warn("[FacultyService] - Username not found with this \"+facultyEmailID +\" Email address");
			throw new FacultyException("Username not found with this "+facultyEmailID +" Email address" );
		}
		
	}
	public ResponseEntity<?> updateFacultyDetails(Faculty faculty)throws Exception {
		String facultyemail=faculty.getFacultyEmail();
		if (facultyRepo.existsById(facultyemail)) {
			facultyRepo.save(faculty);
			facultyLog.info("[FacultyService] - faculty updated his details");
			 return new ResponseEntity<>("your are updated ",HttpStatus.OK);
		}facultyLog.error("[FacultyService] -  faculty tries  updated his detaisl with wrong email");
		throw new FacultyException("your are not updated please check the email once ");
	}
	public List<Query> allQueries() {
		facultyLog.info("[FacultyService] - Faculty viewed all queryies");
		return queryRepo.findAll();
		
		
	}
	public Query viewQuery(Integer queryId)throws Exception {
		if (queryRepo.existsById(queryId)) {
			Optional<Query> queryOptional=queryRepo.findById(queryId);
			   Query query1=queryOptional.get();
			   facultyLog.info("[FacultyService] - The faculty viewed the query with the query ID");
			   return query1;
		}facultyLog.warn("[FacultyService] - In an attempt to view a query with an invalid query ID, faculty uses the wrong query ID");
		throw new QueryException("course doesnot exist with given Id");
		
		   
		
	}
	public Query answerForQuery(Integer courseId,String answer, String FacultyEmail)throws QueryException{
		if (queryRepo.existsById(courseId)) {
			if (facultyRepo.existsById(FacultyEmail)) {
				
			
			Optional<Query> queryOptional=queryRepo.findById(courseId);
			Query query=queryOptional.get();
			Optional<Faculty> facultyOptional=facultyRepo.findById(FacultyEmail);
			Faculty faculty=facultyOptional.get();
			
			query.setAnswer(answer);
			query.setFaculty(faculty);
			facultyLog.info("[FacultyService] - An answer to a question was given by faculty");
			
			return queryRepo.save(query);
			}facultyLog.warn("[FacultyService] - faculty doesnt not exist with givem email address");
			throw new QueryException("faculty doesnt not exist with givem email address");
		}
		facultyLog.warn("[FacultyService] - Incorrect query ID is used by the faculty to answer the query.");
		throw new QueryException("We are unable to match the query ID provided with the quers in database.");
		
	}
	public Faculty viewFacultyProfile(Faculty faculty)throws FacultyException {
		String facultyEmail=faculty.getFacultyEmail();
		if (facultyRepo.existsById(facultyEmail)) {
			Optional<Faculty> facultyOptional=facultyRepo.findById(facultyEmail);
			Faculty faculty2=facultyOptional.get();
			facultyLog.info("[FacultyService] - The faculty attempted to update his information, and it was successfully updated");
			return faculty2;
		}facultyLog.warn("[FacultyService] - A faculty member attempted to update his details with the wrong email address");
		throw new FacultyException("Your email address is incorrect. Please enter your correct email address");
		
	}

}
