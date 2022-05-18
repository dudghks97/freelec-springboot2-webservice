package com.jojoldu.book.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class) //
@WebMvcTest(controllers = HelloController.class)    // Web 에 집중 가능한 어노테이션. 선언 시 @Service, @Component, @Respository 등 사용 불가능.
public class HelloControllerTest {

    @Autowired  // 스프링이 관리하는 빈(Bean) 주입
    private MockMvc mvc;
    // 실제 객체와 비슷하지만 테스트에 필요한 기능만 가지는 가짜 객체를 만들어서 애플리케이션 서버에 배포하지 않고도 스프링 MVC 동작 재현이 가능한 클래스
    // 웹 API 테스트 시 사용. 스프링 MVC 테스트의 시작점. MockMvc 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트 가능

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        // 어떤 이유에서인지는 확인되지 않았으나, 9~11 라인의 import 문 없이는 정상 동작을 하지 않았음
        mvc.perform(get("/hello"))  // MockMvc 를 통해 /hello 주소로 HTTP GET 요청
                .andExpect(status().isOk())     // mvc.perform 의 결과 검증. HTTP Header 의 Status 검증. - 200, 404, 500 등의 상태 검증
                .andExpect(content().string(hello));    // mvc.perform 의 결과 검증. 응답 본문의 내용 검증. Controller 에서 "hello" 를 리턴하기 때문에, 이 값이 맞는지 검증.
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name", is(name)))
                        .andExpect(jsonPath("$.amount", is(amount)));
    }
}
