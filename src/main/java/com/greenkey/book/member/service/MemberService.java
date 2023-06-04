package com.greenkey.book.member.service;

import com.greenkey.book.member.domain.Member;
import com.greenkey.book.member.domain.MemberRepository;
import com.greenkey.book.member.dto.MemberFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

// 2023.6.4(일) 16h50
@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long save(MemberFormDto memberFormDto) {
        validateDuplicateMember(memberFormDto.getEmail());
        String encodedPassword = passwordEncoder.encode(memberFormDto.getPassword()); // 나의 궁금증 = 비밀번호 암호화는 어디에서 처리하는 것이 좋을까?
        return memberRepository.save(memberFormDto.toEntity(encodedPassword)).getId();
    }

    private void validateDuplicateMember(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isPresent()) {
            throw new IllegalStateException("이미 가입된 회원입니다");
//            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
        }
    }

    // 2023.6.4(일) 18h25
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        Member findMember = optionalMember.orElseThrow(() -> new UsernameNotFoundException(email));
        return User.builder()
                .username(findMember.getEmail())
                .password(findMember.getPassword()) // 나의 질문 = 암호화된 상태로 build해도 괜찮은가?
                .roles(findMember.getRole().toString())
                .build();
    }
}
