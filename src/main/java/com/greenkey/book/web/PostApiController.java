package com.greenkey.book.web;

import com.greenkey.book.service.post.PostService;
import com.greenkey.book.web.dto.PostResponseDto;
import com.greenkey.book.web.dto.PostSaveRequestDto;
import com.greenkey.book.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// 2023.5.25(목) 23h55
@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    // 2023.5.26(금) 1h5
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostResponseDto findById(@PathVariable Long id) {
        return postService.findById(id);
    }
}
