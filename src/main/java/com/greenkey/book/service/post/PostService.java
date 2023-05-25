package com.greenkey.book.service.post;

import com.greenkey.book.domain.post.PostRepository;
import com.greenkey.book.web.dto.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 2023.5.26(금) 0h15
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }
}
