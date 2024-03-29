package com.greenkey.book.post.service;

import com.greenkey.book.post.domain.Post;
import com.greenkey.book.post.domain.PostRepository;
import com.greenkey.book.post.dto.PostResponseDto;
import com.greenkey.book.post.dto.PostSaveRequestDto;
import com.greenkey.book.post.dto.PostUpdateRequestDto;
import com.greenkey.book.web.dto.PostListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        /* dirty checking
        JPA의 entity manager가 활성화된 상태로 트랜잭션 안에서 db로부터 데이터 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태 -> 이 상태에서 해당 데이터 값 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분 반영 -> entity 객체 값 변경하면 별도로 update 쿼리 날릴 필요 없음
         */

        return id;
    }

    public PostResponseDto findById(Long id) {
        Post entity = postRepository.findById(id).orElseThrow(() -> new IllegalCallerException("해당 게시글이 없습니다. id = " + id));
        return new PostResponseDto(entity);
    }

    // 2023.6.25(일) 17h10 게시글 전체 조회
    @Transactional(readOnly = true) // 트랜잭션 범위는 유지 + 조회 기능만 남겨 조회 속도 개선
    public List<PostListResponseDto> findAllDesc() {
        return postRepository.findAllByOrderById().stream()
                .map(post -> new PostListResponseDto(post))
                .collect(Collectors.toList());
    }

    // 2023.6.26(월) 20h40
    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalCallerException("해당 게시글이 없습니다. id = " + id));
        postRepository.delete(post); // 이렇게 엔티티를 매개변수로 넘겨 삭제할 수도 있고, deleteById 메서드 이용해서 id로 삭제할 수도 있음
//        postRepository.deleteById(id);
    }

}
