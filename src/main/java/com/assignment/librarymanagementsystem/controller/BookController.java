package com.assignment.librarymanagementsystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.librarymanagementsystem.dto.BookDto;
import com.assignment.librarymanagementsystem.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {

	private BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/allBooks")
	public ResponseEntity<List<BookDto>> getBooks() {
		List<BookDto> bookDtos = bookService.getAvailableBooks();
		return ResponseEntity.status(HttpStatus.OK).body(bookDtos);
	}

	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody BookDto bookDto) {
		bookService.create(bookDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Book created");
	}

	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody BookDto bookDto) {
		bookService.update(bookDto);
		return ResponseEntity.status(HttpStatus.OK).body("Book updated");
	}

	@GetMapping("/searchByTitleAndAuthor")
	public ResponseEntity<List<BookDto>> searchByTitleAndAuthor(@RequestParam("title") String title,
			@RequestParam("author") String author) {
		List<BookDto> bookDtos = bookService.searchByTitleAndAuthor(title, author);
		return ResponseEntity.status(HttpStatus.CREATED).body(bookDtos);
	}

}
