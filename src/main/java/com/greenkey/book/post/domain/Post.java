package com.greenkey.book.post.domain;

import com.greenkey.book.audit.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 2023.5.24(수) 23h50
// domain = S/W에 대한 요구사항 또는 문제 영역 e.g. 게시글, 댓글, 회원, 정산, 결제 등
// entity 클래스 = db 테이블과 매칭
@Getter // Lombok -> 코드 변경량 최소화
@NoArgsConstructor // Lombok
@Entity // JPA, 테이블과 링크될 + 테이블 생성 및 스키마 변경 시 기준이 되는 + db와 맞닿은 + 서비스 클래스 및 비즈니스 로직 동작의 기준이 되는, 핵심 클래스
public class Post extends BaseTimeEntity {
    @Id // 해당 테이블의 pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk 생성 규칙, IDENTITY = auto-increment
    private Long id;

    @Column(length = 500, nullable = false) // 굳이 선언하지 않아도, 이 클래스의 필드는 모두 컬럼이 됨 + 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용 <- 문자열 기본 = VARCHAR(255)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) // 컬럼의 타입을 TEXT로 변경
    private String content;

    private String author;

    @Builder // 빌더 패턴 클래스 생성 + 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Post(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    /* 이 entity가 테이블로 생성될 때 쿼리
    drop table if exists post

    create table post (
       id bigint not null auto_increment,
        author varchar(255),
        content TEXT not null,
        title varchar(500) not null,
        primary key (id)
    ) engine=InnoDB
     */

    // setter 생성은 지양 <- setter 사용 시 해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드 상으로 명확하게 구분할 수 없음
    // 해당 필드의 값 변경이 필요한 경우, 그 목적과 의도를 명확히 나타내는 메서드 추가

    // 2023.5.26(금) 1h15
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
