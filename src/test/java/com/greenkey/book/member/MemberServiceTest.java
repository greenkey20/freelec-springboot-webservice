package com.greenkey.book.member;

import com.greenkey.book.member.domain.Member;
import com.greenkey.book.member.domain.MemberRepository;
import com.greenkey.book.member.dto.MemberFormDto;
import com.greenkey.book.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 2023.6.4(일) 17h15
@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
public class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private MemberRepository memberRepository;

    public MemberFormDto createMemberFormDto() {
        return MemberFormDto.builder()
                .name("강트롬")
                .email("trommblanc@email.com")
                .password("user")
                .address("서울시 마포구")
                .build();
    }

    @Test
    @DisplayName("회원 가입 테스트")
    public void saveMemberTest() {
        // given
        MemberFormDto memberFormDto = createMemberFormDto();

        // when
        Long savedMemberId = memberService.save(memberFormDto);

        // then
        assertThat(savedMemberId).isGreaterThan(0L);
    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    public void saveDuplicateMemberTest() {
        // given
        MemberFormDto memberForm1 = createMemberFormDto();
        MemberFormDto memberForm2 = createMemberFormDto();
        memberService.save(memberForm1);

        // when
        Throwable e = assertThrows(IllegalStateException.class, () -> {
            memberService.save(memberForm2);
        });

        // then
        assertEquals("이미 가입된 회원입니다", e.getMessage());
    }
}
