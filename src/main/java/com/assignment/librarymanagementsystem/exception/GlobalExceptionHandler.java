package com.assignment.librarymanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(BookAlreadyBorrowedException.class)
	public ResponseEntity<String> handleBookAlreadyBorrowed(BookAlreadyBorrowedException ex) {
		String errorMessage = ex.getMessage();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}
}
