package com.prudhviraj.elearning.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.prudhviraj.elearning.entity.ExceptionInfo;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(AdminException.class)
	 public @ResponseBody ExceptionInfo getAdminException(AdminException e,HttpServletRequest request) {
		 return new ExceptionInfo(LocalDateTime.now(),e.getMessage(), request.getRequestURI());
		
	}
	@ExceptionHandler(CourseException.class)
	 public @ResponseBody ExceptionInfo getCourseException(CourseException e,HttpServletRequest request) {
		 return new ExceptionInfo(LocalDateTime.now(),e.getMessage(), request.getRequestURI());
		
	}
	@ExceptionHandler(FacultyException.class)
	 public @ResponseBody ExceptionInfo getFacultyException(FacultyException e,HttpServletRequest request) {
		 return new ExceptionInfo(LocalDateTime.now(),e.getMessage(), request.getRequestURI());
		
	}
	@ExceptionHandler(PaymentException.class)
	 public @ResponseBody ExceptionInfo getPaymentException(PaymentException e,HttpServletRequest request) {
		 return new ExceptionInfo(LocalDateTime.now(),e.getMessage(), request.getRequestURI());
		
	}
	@ExceptionHandler(QueryException.class)
	 public @ResponseBody ExceptionInfo getQueryException(QueryException e,HttpServletRequest request) {
		 return new ExceptionInfo(LocalDateTime.now(),e.getMessage(), request.getRequestURI());
		
	}
	@ExceptionHandler(StudentException.class)
	 public @ResponseBody ExceptionInfo getStudentException(StudentException e,HttpServletRequest request) {
		 return new ExceptionInfo(LocalDateTime.now(),e.getMessage(), request.getRequestURI());
		
	}
	

}
