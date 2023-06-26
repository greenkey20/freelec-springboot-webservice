package com.greenkey.book.web.controller;

import com.greenkey.book.post.dto.PostResponseDto;
import com.greenkey.book.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// 2023.5.27(토) 16h50
@RequiredArgsConstructor
@Controller // 나의 질문 = @RestController와의 차이점?
public class IndexController {
    // 2023.6.25(일) 17h30 추가/변경
    private final PostService postService;

    /**
     * view resolver는 '이 url 매핑하는 페이지의 경로 + 이 메서드의 반환 자료 + 파일 확장자' 처리
     * view resolver = url 요청의 결과를 전달할 타입 및 값 지정하는 관리자
     * @return
     */
    @GetMapping("/")
    public String index(Model model) { // Model = 서버 템플릿 엔진에서 사용할 수 있는 객체 저장
        model.addAttribute("posts", postService.findAllDesc()); // posts라는 키에 대한 속성으로 조회 결과를 index.mustache에 전달
        return "index";
    }

    // 2023.6.23(금) 22h45
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    // 2023.6.26(월) 20h30
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostResponseDto dto = postService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
