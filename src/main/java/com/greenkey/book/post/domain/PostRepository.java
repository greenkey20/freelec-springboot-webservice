package com.greenkey.book.post.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// 2023.5.25(목) 0h5
// db layer 접근자, dao
public interface PostRepository extends JpaRepository<Post, Long> { // 기본적인 CRUD 메서드 자동으로 생성

    // 2023.6.25(일) 17h10 게시글 전체 조회
    @Query("SELECT p FROM Post P ORDER BY p.id DESC")
    List<Post> findAllDesc();

    List<Post> findAllByOrderById();
}
