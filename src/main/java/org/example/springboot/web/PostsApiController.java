package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.services.posts.PostsService;
import org.example.springboot.web.dto.PostsResponseDto;
import org.example.springboot.web.dto.PostsSaveRequestDto;
import org.example.springboot.web.dto.PostsUpdateDto;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    // service 연결
    private final PostsService postsService;

    /**
     * api 요청시 데이터를 서비스단으로 넘겨 글을 등록한다
     * @param requestDto
     * @return Long
     */
    @PostMapping("/api/v1/post")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    /**
     * 글 번호를 받아 수정한다.
     */
    @PutMapping("/api/v1/post/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateDto updateDto){
        return postsService.update(id, updateDto);
    }

    /**
     * 글을 조회한다
     */
    @GetMapping("/api/v1/post/{id}")
    public PostsResponseDto findPostById(@PathVariable Long id){
        return postsService.findPostById(id);
    }

    /*
     * PROCESS :
     view  <-dto->  RestController  <-dto->  Service  <-entity->  Repository  <-entity->  DB

     TODO. What is the Spring Web Layer?
     * Web Layer
        - View
        - RestController / Controller : request/reponse 처리

     * Service Layer
        - Service      : 트랜잭션과 도메인 간 순서 보장
                         dto 내부에서 Entity 객체에 값 할당해주는 메소드 호출 가능 -> 값 할당된 Entity 객체를 repo에 넘겨 DB insert

     * Repository Layer
        - Repository   : 데이터 저장소에 접근

     * DTO          : 계층 간 데이터 전달을 위한 객체 (Data Transfer Object)

     * Domain Model
        - Entity       : 해당 클래스를 기준으로 테이블이 생성/변경된다.
                         * 반드시 Dto와 분리해서 사용해야 한다.
                         DTO    : View Layer를 위한 클래스
                         Entity : DB Layer 클래스
     *
     *
     * Bean 주입 방법
     *      - Autowired
     *      - constructor (권장)
     *      - setter
     *
     * lombok 어노테이션을 이용하면 의존 관계가 변경될 때마다(변수값 추가/삭제) 생성자 코드를 계속 수정해야 하는 번거로움을 줄일 수 있다.
        - @RequiredArgsConstructor : final이 선언된 모든 필드를 인자값으로 가진 생성자를 생성한다.
     */
}
