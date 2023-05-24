package com.greenkey.book.domain.posts;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 2023.5.24(수) 23h50
// domain = S/W에 대한 요구사항 또는 문제 영역 e.g. 게시글, 댓글, 회원, 정산, 결제 등
// entity 클래스 = db 테이블과 매칭
@Getter // Lombok
@NoArgsConstructor // Lombok
@Entity // JPA, 테이블과 링크될 클래스
public class Post {
    @Id // 해당 테이블의 pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk 생성 규칙, IDENTITY = auto-increment
    private Long id;

    @Column(length = 500, nullable = false) // 굳이 선언하지 않아도, 이 클래스의 필드는 모두 컬럼이 됨 + 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Post(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
