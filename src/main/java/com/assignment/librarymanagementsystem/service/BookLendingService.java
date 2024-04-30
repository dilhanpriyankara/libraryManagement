package com.assignment.librarymanagementsystem.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.librarymanagementsystem.dto.MemberBookDto;
import com.assignment.librarymanagementsystem.entity.Book;
import com.assignment.librarymanagementsystem.entity.MemberBook;
import com.assignment.librarymanagementsystem.exception.BookAlreadyBorrowedException;
import com.assignment.librarymanagementsystem.repository.BookLendingRepository;
import com.assignment.librarymanagementsystem.repository.BookRepository;

@Service
public class BookLendingService {

	private BookLendingRepository bookLendingRepository;
	private BookRepository bookRepository;

	public BookLendingService(BookLendingRepository bookLendingRepository, BookRepository bookRepository) {
		this.bookLendingRepository = bookLendingRepository;
		this.bookRepository = bookRepository;
	}

	@Transactional
	public void lendingBook(MemberBookDto memberBookDto) {
		ModelMapper modelMapper = new ModelMapper();
		MemberBook memberBook = new MemberBook();
		modelMapper.map(memberBookDto, memberBook);

		MemberBook existingMemberBook = bookLendingRepository.findByBookAndMemberAndDateinIsNull(memberBook.getBook(),
				memberBook.getMember());
		if (existingMemberBook != null) {
			throw new BookAlreadyBorrowedException("The user has already borrowed the same book.");
		}

		bookLendingRepository.save(memberBook);
		Optional<Book> bookOptional = bookRepository.findById(memberBookDto.getBook().getId());
		if (bookOptional.isPresent()) {
			Book book = bookOptional.get();
			int remainingQuantity = book.getQuantity() - 1;
			book.setQuantity(remainingQuantity);
			bookRepository.save(book);
		}
	}

	@Transactional
	public void returnBook(MemberBookDto memberBookDto) {
		ModelMapper modelMapper = new ModelMapper();
		MemberBook memberBook = new MemberBook();
		modelMapper.map(memberBookDto, memberBook);
		MemberBook existingMemberBook = bookLendingRepository.findByBookAndMemberAndDateinIsNull(memberBook.getBook(),
				memberBook.getMember());
		existingMemberBook.setDatein(memberBookDto.getDatein());
		bookLendingRepository.save(existingMemberBook);
		Optional<Book> bookOptional = bookRepository.findById(memberBookDto.getBook().getId());
		if (bookOptional.isPresent()) {
			Book book = bookOptional.get();
			int remainingQuantity = book.getQuantity() + 1;
			book.setQuantity(remainingQuantity);
			bookRepository.save(book);
		}
	}

}
