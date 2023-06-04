package com.greenkey.book.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 2023.6.4(일) 16h30
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
