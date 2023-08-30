package com.prudhviraj.elearning.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.prudhviraj.elearning.entity.Faculty;
import com.prudhviraj.elearning.entity.Query;
import com.prudhviraj.elearning.exception.FacultyException;
import com.prudhviraj.elearning.exception.QueryException;

public interface FacultyInterface {
	public Faculty registerAsFaculty(Faculty faculty)throws Exception;
	public Faculty facultyLogin(String facultyEmailID, String facultyPassword) throws Exception;
	public ResponseEntity<?> updateFacultyDetails(Faculty faculty)throws Exception;
	public List<Query> allQueries();
	public Query viewQuery(Integer queryId)throws Exception ;
	public Query answerForQuery(Integer courseId,String answer, String FacultyEmail)throws QueryException;
	public Faculty viewFacultyProfile(Faculty faculty)throws FacultyException ;

}
