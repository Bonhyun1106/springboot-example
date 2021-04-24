package org.example.springboot.web;

import org.assertj.core.api.Assertions;
import org.example.springboot.domain.posts.Posts;
import org.example.springboot.domain.posts.PostsRepository;
import org.example.springboot.web.dto.PostsSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록(){

        // given
        String title = "title";
        String content = "contents";
        String url = "http://localhost:" + port + "/api/v1/post";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder().title(title).content(content).author("gg").build();

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> posts = postsRepository.findAll();
        Assertions.assertThat(posts.get(0).getTitle()).isEqualTo(title);
        Assertions.assertThat(posts.get(0).getContent()).isEqualTo(content);
    }

    /*
        @RunWith : JUnit 테스트를 실행할 때 내장된 Ruuner를 실행한다
        @SpringBootTest : 여러 단위 테스트를 하나의 통합된 테스트로 수행할 떄 사용한다


        ERROR Note
        에러 종류 : restclientexception

        원인 : restclientexception 전에 값을 주입해 주는 부분에서 NullPointerException이 발생
              PostsService에서 @RequiredArgsConstructor로 값을 넣어주고 있으나 PostsRepository를 주입하는 과정에서 final 선언을 해주지 않아 값을 주입하지 못하고 있던것

        해결 : Service단에서 PostsRepository 선언?시 'final' 키워드 추가

        참고 URL :
        https://velog.io/@agugu95/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-JPA-%EC%9B%B9-%EC%95%B1-%EA%B5%AC%EC%B6%95-%EC%9B%B9-%EA%B3%84%EC%B8%B5-%EA%B0%9C%EB%B0%9C

        -> StackTrace를 읽는 방법을 익혀야한다 !
     */

}
