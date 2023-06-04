package com.greenkey.book.member.dto;

import com.greenkey.book.constant.Role;
import com.greenkey.book.member.domain.Member;
import lombok.*;

// 2023.6.4(일) 16h5
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberFormDto {
    private String name;
    private String email;
    private String password;
    private String address;

    @Builder
    public MemberFormDto(String name, String email, String password, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public Member toEntity(String encodedPassword) {
        return Member.builder()
                .name(name)
                .email(email)
                .password(encodedPassword)
                .address(address)
                .build();
    }
}
