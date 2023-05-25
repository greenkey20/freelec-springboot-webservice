package com.greenkey.book.service.post;

import com.greenkey.book.domain.post.Post;
import com.greenkey.book.domain.post.PostRepository;
import com.greenkey.book.web.dto.PostResponseDto;
import com.greenkey.book.web.dto.PostSaveRequestDto;
import com.greenkey.book.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 2023.5.26(금) 0h15
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    // 2023.5.26(금) 1h15
    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalCallerException("해당 게시글이 없습니다. id = " + id));
        post.update(requestDto.getTitle(), requestDto.getContent()); // update 기능에서 db에 쿼리를 날리는 부분 없음 <- JPA 영속성 컨텍스트(엔티티를 영구 저장하는 환경)

        return id;
    }

    public PostResponseDto findById(Long id) {
        Post entity = postRepository.findById(id).orElseThrow(() -> new IllegalCallerException("해당 게시글이 없습니다. id = " + id));
        return new PostResponseDto(entity);
    }
}
