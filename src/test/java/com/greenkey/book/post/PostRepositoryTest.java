package com.greenkey.book.post;

import com.greenkey.book.post.domain.Post;
import com.greenkey.book.post.domain.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
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
        /* insert
        into
            post
            (author, content, created_date, modified_date, title)
        values
            (?, ?, ?, ?, ?)
         */

        // when
        List<Post> postsList = postRepository.findAll();

        // then
        Post post = postsList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

    // 2023.5.27(토) 16h5
    @Test
    public void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.of(2023, 5, 27, 0, 0, 0);
        postRepository.save(Post.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // when
        List<Post> allPosts = postRepository.findAll();

        // then
        Post post = allPosts.get(0);
        System.out.println("createdDate = " + post.getCreatedDate() + ", modifiedDate = " + post.getModifiedDate()); // createdDate = 2023-05-27T16:08:42.226973, modifiedDate = 2023-05-27T16:08:42.226973

        assertThat(post.getCreatedDate()).isAfter(now);
        assertThat(post.getModifiedDate()).isAfter(now);
    }
}
