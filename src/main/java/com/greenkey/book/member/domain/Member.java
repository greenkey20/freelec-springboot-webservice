package com.greenkey.book.member.domain;

import com.greenkey.book.audit.BaseTimeEntity;
import com.greenkey.book.constant.Role;
import com.greenkey.book.member.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

// 2023.6.4(일) 16h15
@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(String name, String email, String password, String address, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    // Member 엔티티에 회원 생성하는 메서드를 만듦 -> 코드 변경 시에도 여기만 수정하면 되는 이점 있음
    public Member create(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .name(memberFormDto.getName())
                .email(memberFormDto.getEmail())
                .password(passwordEncoder.encode(memberFormDto.getPassword()))
                .address(memberFormDto.getAddress())
                .role(Role.USER)
                .build();
    }
}
