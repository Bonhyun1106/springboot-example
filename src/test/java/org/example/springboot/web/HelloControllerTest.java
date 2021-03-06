package org.example.springboot.web;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// JUnit에 내장된 SpringRunner.class 실행자를 실행시킨다 -> 스프링부트 테스트와 JUnit 사이에 연결 역할
@RunWith(SpringRunner.class)
// springMVC 어노테이션. @Controller, @ControllerAdvice 등 사용 가능
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;    // 웹 API 테스트에 사용. 스프링MVC의 시작점이다. MockMvc클래스를 통해 HTTP GET/POST등에 대한 API테스트 진행 가능

    @Test
    public void returnHello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/"))             // 해당주소로 HTTP GET 요청
                .andExpect(status().isOk())         // mvc.perform의 결과 검증. HTTP Header - status코드값 200인지 검증(통신 성공)
                .andExpect(content().string(hello));// 응답 body의 내용이 hello와 같은지 검증한다.
    }

    @Test
    public void returnHelloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Is.is(name)))
                .andExpect(jsonPath("$.amount", Is.is(amount)));
    }

}
