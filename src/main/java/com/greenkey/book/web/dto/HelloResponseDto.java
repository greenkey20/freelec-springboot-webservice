package com.greenkey.book.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

// 2023.5.7(일) 22h35
@Getter
//@AllArgsConstructor
@RequiredArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
