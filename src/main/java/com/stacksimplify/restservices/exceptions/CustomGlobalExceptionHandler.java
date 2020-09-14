package com.stacksimplify.restservices.exceptions;

//import java.sql.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		CustomErrorDetails customerErrorDetails = new CustomErrorDetails(new Date(), "message from MethodArgumentNotValid Exception in GEH", ex.getLocalizedMessage() );
		return new ResponseEntity<>(customerErrorDetails, HttpStatus.BAD_REQUEST);
	}
	
	
	//HttpRequestMethodNotSupported
	
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails customerErrorDetails = new CustomErrorDetails(new Date(), "message from handleHttpRequestMethodNotSupported Exception in GEH", ex.getLocalizedMessage() );
		return new ResponseEntity<>(customerErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
		
	}
	
	//UserName Not Found Exception
	@ExceptionHandler(UserNameNotFoundException.class)
	public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex, WebRequest request)
	{

		CustomErrorDetails customerErrorDetails = new CustomErrorDetails(new Date(), ex.getMessage(), ex.getLocalizedMessage());
		return new ResponseEntity<>(customerErrorDetails, HttpStatus.NOT_FOUND);
		
	}
	
	//ConstraintVoilationException
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintVoilationException(ConstraintViolationException ex, WebRequest request)
	{
		CustomErrorDetails customerErrorDetails = new CustomErrorDetails(new Date(), ex.getMessage(), ex.getLocalizedMessage());
		return new ResponseEntity<>(customerErrorDetails, HttpStatus.BAD_REQUEST);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
