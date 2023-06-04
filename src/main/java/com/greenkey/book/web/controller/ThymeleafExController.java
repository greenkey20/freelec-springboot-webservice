package com.greenkey.book.web.controller;

import com.greenkey.book.item.dto.ItemDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

// 2023.6.4(일) 14h15
@RestController
@RequestMapping("/thymeleaf")
public class ThymeleafExController {

    @GetMapping("/ex01")
    public String thymeleafExample01(Model model) {
        model.addAttribute("data", "Thymeleaf 예제입니다");
        return "thymeleafEx/thymeleafEx01";
    }

    @GetMapping("/ex02")
    public String thymeleafExample02(Model model) {
        ItemDto itemDto = ItemDto.builder()
                .name("테스트 상품1")
                .price(10000)
                .description("상품 상세 설명")
                .createdAt(LocalDateTime.now())
                .build();

        model.addAttribute("itemDto", itemDto);
        return "thymeleafEx/thymeleafEx02";
    }
}
