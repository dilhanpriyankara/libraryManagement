package com.assignment.librarymanagementsystem.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.assignment.librarymanagementsystem.dto.MemberDto;
import com.assignment.librarymanagementsystem.entity.Member;
import com.assignment.librarymanagementsystem.repository.MemberRepository;

@Service
public class MemberService {

	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public void create(MemberDto memberDto) {
		ModelMapper modelMapper = new ModelMapper();
		Member member = new Member();
		modelMapper.map(memberDto, member);
		memberRepository.save(member);		
	}
}
