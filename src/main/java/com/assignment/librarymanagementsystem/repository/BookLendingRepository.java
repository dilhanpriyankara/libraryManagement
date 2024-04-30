package com.assignment.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.librarymanagementsystem.entity.Book;
import com.assignment.librarymanagementsystem.entity.Member;
import com.assignment.librarymanagementsystem.entity.MemberBook;

@Repository
public interface BookLendingRepository extends JpaRepository<MemberBook, Long> {
	MemberBook findByBookAndMember(Book book, Member member);

	MemberBook findByBookAndMemberAndDateinIsNull(Book book, Member member);
}
