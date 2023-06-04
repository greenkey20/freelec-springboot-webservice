package com.greenkey.book.post.dto;

import com.greenkey.book.post.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 2023.5.26(금) 0h15
// dto-entity 클래스는 꼭 분리해서 사용 e.g. Controller에서 결과값으로 여러 테이블을 조인해서 줘야 하는 경우 등 빈번
@Getter
@NoArgsConstructor
public class PostSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
