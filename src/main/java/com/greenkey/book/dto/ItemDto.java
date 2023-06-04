package com.greenkey.book.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
// 2023.6.4(일) 14h30
public class ItemDto {
    private Long id;
    private String name;
    private int price;
    private String description;
    private String sellStatusCode;
    private LocalDateTime createdAt;
}