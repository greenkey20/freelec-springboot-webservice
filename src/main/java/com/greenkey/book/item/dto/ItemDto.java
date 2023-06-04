package com.greenkey.book.item.dto;

import lombok.*;

import java.time.LocalDateTime;

// 2023.6.4(일) 14h30
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ItemDto {
    private Long id;
    private String name;
    private int price;
    private String description;
    private String sellStatusCode;
    private LocalDateTime createdAt;
}