package com.assignment.librarymanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.librarymanagementsystem.dto.MemberBookDto;
import com.assignment.librarymanagementsystem.service.BookLendingService;

@RestController
@RequestMapping("/api/bookLending")
public class BookLendingController {	
	
	private BookLendingService bookLendingService; 
	
	public BookLendingController(BookLendingService bookLendingService) {		
		this.bookLendingService = bookLendingService;
	}	
	
	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody MemberBookDto memberBookDto) {		
		bookLendingService.lendingBook(memberBookDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Book lending success");
	}	
	
	@PutMapping("/returnBook")
	public ResponseEntity<String> returnBook(@RequestBody MemberBookDto memberBookDto) {	  
	  bookLendingService.returnBook(memberBookDto);
	  return ResponseEntity.status(HttpStatus.OK).body("Book retuned");
	}

}
