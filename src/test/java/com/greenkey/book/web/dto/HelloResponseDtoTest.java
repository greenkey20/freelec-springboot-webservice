package com.greenkey.book.web.dto;

import com.greenkey.book.web.dto.HelloResponseDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat; // assertj라는 테스트 검증 라이브러리

// 2023.5.7(일) 22h45
public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name); // assertThat(검증 대상)에 있는 값과 isEqualTo의 값 비교
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
