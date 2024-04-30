package com.assignment.librarymanagementsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.assignment.librarymanagementsystem.dto.BookDto;
import com.assignment.librarymanagementsystem.entity.Book;
import com.assignment.librarymanagementsystem.repository.BookRepository;

@Service
public class BookService {

	private BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public void create(BookDto bookDto) {
		ModelMapper modelMapper = new ModelMapper();
		Book book = new Book();
		modelMapper.map(bookDto, book);
		bookRepository.save(book);
		System.out.println(book);
	}

	public List<BookDto> getAvailableBooks() {
		boolean isArchived = false;
		int quantity = 0;
		List<Book> books = bookRepository.findAllByIsArchivedAndQuantityGreaterThan(isArchived, quantity);
		List<BookDto> bookDtos = getBookDto(books);
		return bookDtos;
	}

	public void update(BookDto bookDto) {
		Optional<Book> bookOptional = bookRepository.findById(bookDto.getId());
		if (bookOptional.isPresent()) {
			Book book = bookOptional.get();
			book.setIsbn(bookDto.getIsbn());
			book.setAuthor(bookDto.getAuthor());
			book.setTitle(bookDto.getTitle());
			book.setIsArchived(bookDto.getIsArchived());
			book.setQuantity(bookDto.getQuantity());
			bookRepository.save(book);
		}
	}

	public List<BookDto> searchByTitleAndAuthor(String title, String author) {
		List<Book> books = bookRepository.findByTitleContainingAndAuthorContaining(title, author);
		List<BookDto> bookDtos = getBookDto(books);
		return bookDtos;
	}

	private List<BookDto> getBookDto(List<Book> books) {
		ModelMapper modelMapper = new ModelMapper();
		List<BookDto> bookDtos = new ArrayList<>();
		for (Book book : books) {
			BookDto bookDto = modelMapper.map(book, BookDto.class);
			bookDtos.add(bookDto);
		}
		return bookDtos;
	}
}
