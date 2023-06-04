package com.greenkey.book.post.domain;

import org.springframework.data.jpa.repository.JpaRepository;

// 2023.5.25(목) 0h5
// db layer 접근자, dao
public interface PostRepository extends JpaRepository<Post, Long> { // 기본적인 CRUD 메서드 자동으로 생성
}
