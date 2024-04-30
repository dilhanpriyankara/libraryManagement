package com.assignment.librarymanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.librarymanagementsystem.dto.MemberDto;
import com.assignment.librarymanagementsystem.service.MemberService;

@RestController
@RequestMapping("/api/member")
public class MemberController {	
	
	private MemberService memberService; 
	
	public MemberController(MemberService memberService) {		
		this.memberService = memberService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody MemberDto memberDto) {
		System.out.println(memberDto);
		memberService.create(memberDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Member created");
	}

}
