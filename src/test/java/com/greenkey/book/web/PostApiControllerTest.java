package com.greenkey.book.web;

import com.greenkey.book.domain.post.Post;
import com.greenkey.book.domain.post.PostRepository;
import com.greenkey.book.web.dto.PostSaveRequestDto;
import com.greenkey.book.web.dto.PostUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// 2023.5.26(금) 0h25
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// Tomcat initialized with port(s): 0 (http) + Tomcat started on port(s): 61687 (http) with context path ''
public class PostApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired // 나의 질문 = 테스트 클래스에서는 @Autowired로 의존성 주입받는 이유?
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

    @AfterEach
    public void tearDown() {
        postRepository.deleteAll();
    }

    @Test
    public void Post_등록된다() {
        // given
        String title = "title";
        String content = "content";
        PostSaveRequestDto requestDto = PostSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class); // 나의 질문 = 이게 PostApiController를 호출하는 건가? -> API 문서 읽어봐
        // requestDto = POST 대상 객체 -> insert
        //    into
        //        post
        //        (author, content, title)
        //    values
        //        (?, ?, ?)

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    // 2023.5.27(토) 15h20 ~ 15h35
    @Test
    public void Post_수정된다() {
        // given
        Post savedPost = postRepository.save(Post.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = savedPost.getId();
        String updateTitle = "title2";
        String updateContent = "content2";

        PostUpdateRequestDto requestDto = PostUpdateRequestDto.builder()
                .title(updateTitle)
                .content(updateContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity<PostUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
        /* PostApiController의 update() 호출 = PostService의 update() 호출 = Post 객체 내용 업데이트하여 db에 반영(dirty checking)
        update
        post
        set
            author=?,
            content=?,
            title=?
        where
            id=?
         */

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(updateTitle);
        assertThat(all.get(0).getContent()).isEqualTo(updateContent);
    }
}
