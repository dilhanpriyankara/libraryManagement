package com.assignment.librarymanagementsystem.dto;

import java.util.Date;

public class MemberBookDto {
	private Long id;
	private MemberDto member;
	private BookDto book;
	private Date dateOut;
	private Date datein;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MemberDto getMember() {
		return member;
	}

	public void setMember(MemberDto member) {
		this.member = member;
	}

	public BookDto getBook() {
		return book;
	}

	public void setBook(BookDto book) {
		this.book = book;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Date getDatein() {
		return datein;
	}

	public void setDatein(Date datein) {
		this.datein = datein;
	}

}
