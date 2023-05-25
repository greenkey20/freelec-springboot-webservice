package com.greenkey.book.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 2023.5.26(금) 1h10
@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public PostUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
