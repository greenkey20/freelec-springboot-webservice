package com.greenkey.book.post.dto;

import com.greenkey.book.post.domain.Post;
import lombok.Getter;

// 2023.5.26(금) 1h10
@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
