package org.example.springboot.services.posts;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.posts.Posts;
import org.example.springboot.domain.posts.PostsRepository;
import org.example.springboot.web.dto.PostsResponseDto;
import org.example.springboot.web.dto.PostsSaveRequestDto;
import org.example.springboot.web.dto.PostsUpdateDto;
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
        return savePosts.getId();
    }

    /**
     * 글 수정
     */
    @Transactional
    public Long update(Long id, PostsUpdateDto updateDto){
        Posts post = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("게시글이 없습니다. id=" + id));
        post.updatePost(updateDto.getTitle(), updateDto.getContent());  // TODO : JPA - 영속성 컨텍스트란?
        return id;
    }

    /**
     * 글 조회
     */
    @Transactional
    public PostsResponseDto findPostById(Long id){
        Posts postEntity = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("게시글을 찾을 수 없습니다. 게시글 번호:" + id));
        return new PostsResponseDto(postEntity);    // convert entity to Dto
    }

    /*
        JDK8 - Optional
            OrElseThrow()

        JPA
            - 영속성 컨텍스트(Persistence Context)
                : 엔티티를 영구 저장하는 환경
                  Entity Manager가 활성화된 상태에서, 한 트랜잭션 안의 엔티티는 영속성 컨텍스트가 유지된다.
                  -> update 메소드에서 조회한 Post Entity의 값을 변경해주면 트랜잭션이 끝날 때 자동으로 db update 해준다. TODO : Dirty Check란?
     */
}
