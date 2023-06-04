package com.greenkey.book.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 2023.5.7(일) 19h50
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class) // web 관련 스프링 테스트 애너테이션, JPA 기능 작동x, Controller(Advice) 등 외부 연동과 관련된 부분만 활성화됨
//@SpringBootTest(webEnvironment = RANDOM_PORT)
//@ContextConfiguration(classes = FreelecSpringbootWebserviceApplication.class)
//@MockBean(JpaMetamodelMappingContext.class)
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 bean 주입받기
    private MockMvc mvc; // 스프링 MVC 테스트의 시작점 + web api 테스트 시 사용

//    @MockBean
//    private PostService postService;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

//        mvc.perform(get("/hello"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(hello));
    }

    // 2023.5.7(일) 23h
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 4400;

//        mvc.perform(get("/hello/dto")
//                        .param("name", name)
//                        .param("amount", String.valueOf(amount))) // api 테스트 시 사용될 요청 파라미터(String 값만 허용됨) 설정
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is(name))) // JSON 응답 값을 필드별로 검증하는 메서드
//                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
