package com.assignment.librarymanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.librarymanagementsystem.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findAllByIsArchived(boolean isArchived);

	List<Book> findAllByIsArchivedAndQuantityGreaterThan(boolean isArchived, int quantity);

	List<Book> findByTitleContainingAndAuthorContaining(String title, String author);

}
