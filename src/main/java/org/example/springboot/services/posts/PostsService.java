package org.example.springboot.services.posts;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.posts.Posts;
import org.example.springboot.domain.posts.PostsRepository;
import org.example.springboot.web.dto.PostsSaveRequestDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    // 레파지토리 연결
    private final PostsRepository postsRepository;

    /**
     * 글 등록
     * @param requestDto
     * @return Long
     */
    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        Posts savePosts = postsRepository.save(requestDto.toEntity());
        return savePosts.getId(); // insert한 데이터의 id 반환
    }
}
