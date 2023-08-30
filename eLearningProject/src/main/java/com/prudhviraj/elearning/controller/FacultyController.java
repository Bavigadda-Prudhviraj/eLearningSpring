package com.prudhviraj.elearning.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.prudhviraj.elearning.entity.Faculty;
import com.prudhviraj.elearning.entity.Query;
import com.prudhviraj.elearning.exception.QueryException;
import com.prudhviraj.elearning.repository.FacultyRepository;
import com.prudhviraj.elearning.repository.QueryRepository;
import com.prudhviraj.elearning.service.FacultyService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
	@Autowired
	FacultyService facultyService;
	@Autowired
	QueryRepository queryRepo;
	@Autowired
	FacultyRepository facultyRepo;
	

	@PostMapping("/registerAsFaculty")
	public ResponseEntity<?> registrationAsFaculty(@RequestBody Faculty faculty) throws Exception {
		Faculty faculty2=facultyService.registerAsFaculty(faculty);
		String email=faculty2.getFacultyEmail();
		String password=faculty2.getFacultyPassword();
		EntityModel<Faculty> response=EntityModel.of(faculty2);
		response.add(WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).loginUser(email, password)).withRel("Facultylogin"));
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/Facultylogin")
	public ResponseEntity<?> loginUser(@RequestParam String facultyEmail,@RequestParam String facultyPassword) throws Exception{
		Faculty faculty=facultyService.facultyLogin(facultyEmail, facultyPassword);
		EntityModel<Faculty> entityModel=EntityModel.of(faculty);
		entityModel.add(WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).viweAllQuries()).withRel("viweAllQuries"));
		entityModel.add(WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).updateFacultyDetails(faculty)).withRel("updateFacultyDetails"));
		return new ResponseEntity<>(entityModel, HttpStatus.OK);
	}
	@PutMapping("/updateFacultyDetails")
	public ResponseEntity<?> updateFacultyDetails(@RequestBody Faculty faculty) throws Exception {
		ResponseEntity<?> faculty2=facultyService.updateFacultyDetails(faculty);
		EntityModel<?> facultyEntityModel=EntityModel.of(faculty2);
		facultyEntityModel.add(WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).viewProfile(faculty)).withRel("viewFacultyProfile"));
			return new ResponseEntity<>(facultyEntityModel,HttpStatus.OK);
	}
	@GetMapping("/viweAllQuries")
	public ResponseEntity<?> viweAllQuries() throws Exception {
		//List<Query> queryList=facultyService.allQueries();
		//EntityModel<List<Query>> queryEntityModel=EntityModel.of(queryList);
		//queryEntityModel.add(WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).answerForQuery(null, null, null)).withRel("answerForQuery"));
		
		return new ResponseEntity<>(facultyService.allQueries(),HttpStatus.OK);
	}	
	@GetMapping("/viewQueryById")
	public ResponseEntity<?> viewQueryById(@RequestParam Integer queryId) throws Exception {
		 Query query=facultyService.viewQuery(queryId);
		 EntityModel<Query> response=EntityModel.of(query);
		 response.add(WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).viweAllQuries()).withRel("viweAllQuries"));
		 response.add(WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).answerForQuery(queryId, null, null)).withRel("answerForQuery"));
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	@PutMapping("/answerForQuery")
	public ResponseEntity<?> answerForQuery(@RequestParam Integer queryId,@RequestParam String answer,@RequestParam String facultyEmail)throws Exception {
		//int queryId=query.getQueryId();
		if(queryRepo.existsById(queryId)) {
			Query query=facultyService.answerForQuery(queryId, answer, facultyEmail);
			EntityModel<Query> response1=EntityModel.of(query);
			
			response1.add(WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).viewQueryById(queryId)).withRel("viewQueryById"));
				return new ResponseEntity<>(response1,HttpStatus.OK);
			}throw new QueryException("query doesnt exist with given id");
			
		
	}
	@GetMapping("/viewFacultyProfile")
	public ResponseEntity<?> viewProfile(@RequestBody Faculty faculty) throws Exception {
		ResponseEntity<?> faculty2=facultyService.updateFacultyDetails(faculty);
		
		EntityModel<?> facultyEntityModel=EntityModel.of(faculty2);
		facultyEntityModel.add(WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).updateFacultyDetails(faculty)).withRel("updateFacultyDetails"));
		facultyEntityModel.add(WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).viweAllQuries()).withRel("viweAllQuries"));
		facultyEntityModel.add(WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).answerForQuery(null, null, faculty.getFacultyEmail())).withRel("answerForQuery"));
		return new ResponseEntity<>(facultyEntityModel, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
