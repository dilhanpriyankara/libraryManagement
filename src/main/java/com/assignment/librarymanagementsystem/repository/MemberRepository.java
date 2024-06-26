package com.assignment.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.librarymanagementsystem.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
