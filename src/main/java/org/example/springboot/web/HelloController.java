package org.example.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // == @ResponseBody : 반환값을 JSON 형태로 만들어줌
public class HelloController {

    @GetMapping("/hello")   // HTTP GET요청을 받아들인다 == @RequestMapping(method=Request)
    public String hello(){
        return "hello";
    }
    /*
        내장WAS를 사용하는 이유
        항상 같은 환경에서 스프링부트를 배포할 수 있기 때문
        외장 WAS 사용시 모든 서버의 WAS 종류, 버전, 설정을 일치시켜야 하고, 버전을 올리는 등의 변화에 많은 노동력이 필요하다
     */

}
