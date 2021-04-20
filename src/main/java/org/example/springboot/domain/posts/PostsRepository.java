package org.example.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/*
*   Repository : DB 접근자
*
*   - Entity와 Repository는 한 쌍
*   - JpaRepository<Entity 클래스명, Entity PK 타입>
*   - Repository 생성시 CRUD 메소드가 자동 생성된다
* */
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
