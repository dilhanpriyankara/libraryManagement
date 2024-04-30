package com.assignment.librarymanagementsystem.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "MEMBER_BOOK")
public class MemberBook {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @ManyToOne
	    @JoinColumn(name = "member_id")
	    private Member member;
	    @ManyToOne
	    @JoinColumn(name = "book_id")
	    private Book book;	    
	    private Date dateOut;
	    private Date datein;
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Member getMember() {
			return member;
		}
		public void setMember(Member member) {
			this.member = member;
		}
		public Book getBook() {
			return book;
		}
		public void setBook(Book book) {
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
