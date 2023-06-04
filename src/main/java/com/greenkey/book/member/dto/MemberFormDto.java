package com.greenkey.book.member.dto;

import com.greenkey.book.constant.Role;
import com.greenkey.book.member.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

// 2023.6.4(일) 16h5
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberFormDto {
    @NotBlank(message = "이름은 필수 입력 값입니다")
    private String name;

    @NotBlank(message = "이메일은 필수 입력 값입니다")
    @Email(message = "이메일 형식으로 입력해 주세요")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다")
    @Length(min = 8, max = 16, message = "비밀번호는 8자 이상 16자 이하로 입력해 주세요")
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
