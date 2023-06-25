package com.greenkey.book.web.dto;

import com.greenkey.book.post.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;

// 2023.6.25(일) 17h25 게시글 전체 조회 시 응답 dto
@Getter
public class PostListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public PostListResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}
