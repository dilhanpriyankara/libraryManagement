package com.assignment.librarymanagementsystem.dto;

import java.util.HashSet;
import java.util.Set;
import com.assignment.librarymanagementsystem.entity.MemberBook;

public class MemberDto {
	private Long id;
	private String name;
	private String address;
	private String phoneNumber;
	private Set<MemberBook> memberBooks = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<MemberBook> getMemberBooks() {
		return memberBooks;
	}

	public void setMemberBooks(Set<MemberBook> memberBooks) {
		this.memberBooks = memberBooks;
	}
}
