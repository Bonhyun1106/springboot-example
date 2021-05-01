package org.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing      // JPA Auditing 활성화
@SpringBootApplication  // 최초 시작점. 항상 프로젝트 최상단에 위치해야함
public class Application {
    public static void main(String[] args) {
        // 프로젝트에 내장된 WAS를 실행한다
        SpringApplication.run(Application.class, args);

        /*
         *  내부 WAS를 사용하는 이유는?
         *  항상 같은 환경에서 스프링부트를 배포할 수 있기 때문
         *  외장 WAS 사용시, 모든 서버의 WAS 종류, 버전, 설정을 일치시켜야 하는 어려움 존재.
         */
    }
}
