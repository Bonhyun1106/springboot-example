package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.services.posts.PostsService;
import org.example.springboot.web.dto.PostsSaveRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    /*
     * view
     *      dto 데이터 담아서 전달
     *  RestController / Controller
     *      dto 데이터 담아서 전달
     *  Service
     *      dto 내부에서 Entity 객체에 값 할당해주는 메소드 호출 가능 -> 값 할당된 Entity 객체를 repo에 넘겨 DB insert
     *  Repository
     *      DB 접근
     *
     * Bean 주입 방법
     *      - Autowired
     *      - constructor (권장)
     *      - setter
     * @RequiredArgsConstructor : 생성자로 Bean 객체를 주입받는`다
     */
}
