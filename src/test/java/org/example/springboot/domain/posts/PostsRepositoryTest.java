package org.example.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Test
    public void saveAndFindPosts() {
        //given
        String title = "제목";
        String content = "내용입니다";

        /*
        *    Posts.builder()
        * */
        postsRepository.save(
                Posts.builder()
                        .title(title)
                        .content(content)
                        .author("me@me.com")
                        .build()
        );

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @After
    public void cleanUp(){
        postsRepository.deleteAll();
    }
    /*
    *   @After
    *   여러 테스트가 동시 수행되면 인메모리 DB인 H2에 이전 테스트 데이터가 남아있어 테스트를 실패할 수도 있기 때문에 deleteAll 해준다.
    */

}
