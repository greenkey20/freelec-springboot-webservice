package com.greenkey.book.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 2023.5.27(토) 16h50
@Controller // 나의 질문 = @RestController와의 차이점?
public class IndexController {
    /**
     * view resolver는 '이 url 매핑하는 페이지의 경로 + 이 메서드의 반환 자료 + 파일 확장자' 처리
     * view resolver = url 요청의 결과를 전달할 타입 및 값 지정하는 관리자
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
