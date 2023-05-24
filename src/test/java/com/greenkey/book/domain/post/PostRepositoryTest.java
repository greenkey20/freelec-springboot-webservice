package com.greenkey.book.domain.post;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// 2023.5.25(목) 0h10
@ExtendWith(SpringExtension.class)
//@DataJpaTest // 코드스테이츠 메인프로젝트 시 사용한 어노테이션
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @AfterEach
    public void cleanUp() {
        postRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기_테스트() {
        // given
        String title = "테스트 게시글 제목";
        String content = "테스트 게시글 본문";

        // id 값이 없으면 insert vs 있으면 update 쿼리 실행
        postRepository.save(Post.builder()
                .title(title)
                .content(content)
                .author("greenkey@gg.com")
                .build());

        // when
        List<Post> postsList = postRepository.findAll();

        // then
        Post post = postsList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }


}
