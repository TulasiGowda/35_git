package com.javaexpress.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ErrorAPI handleExcetion(ResourceNotFoundException ex) {
		ErrorAPI errorAPI = new ErrorAPI();
		errorAPI.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorAPI.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorAPI.setDetails(ex.getMessage());
		errorAPI.setTitle("Resource Not found in DB");
		errorAPI.setLocalDateTime(LocalDateTime.now());
		return errorAPI;
	}

	/*
	 * @ExceptionHandler(ResourceNotFoundException.class) public
	 * ResponseEntity<ErrorAPI> handleExcetion1(ResourceNotFoundException ex) {
	 * ErrorAPI errorAPI = new ErrorAPI();
	 * errorAPI.setStatusCode(HttpStatus.BAD_REQUEST.value());
	 * errorAPI.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
	 * errorAPI.setDetails(ex.getMessage());
	 * errorAPI.setTitle("Resource Not found in DB");
	 * errorAPI.setLocalDateTime(LocalDateTime.now()); return new
	 * ResponseEntity<>(errorAPI, HttpStatus.BAD_REQUEST); }
	 */

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorAPI handleExcetion(Exception ex) {
		ErrorAPI errorAPI = new ErrorAPI();
		errorAPI.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorAPI.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		errorAPI.setDetails(ex.getMessage());
		errorAPI.setTitle("Something went wrong");
		errorAPI.setLocalDateTime(LocalDateTime.now());
		return errorAPI;
	}

}
