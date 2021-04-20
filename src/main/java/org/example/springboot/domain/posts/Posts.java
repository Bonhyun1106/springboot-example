package org.example.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 주요 어노테이션을 클래스와 가깝게 둔다. 후에 lombok을 사용하지 않으면 위의 어노테이션만 지우면 되니까 깔끔!
@Getter
@NoArgsConstructor
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column
    private String author;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    /*
    * JPA Annotation
    *   - Entity : 테이블과 매핑될 객체임을 알림
    *   - Id     : 해당 테이블의 PK. Long type으로 하는게 좋음 --> Why?
    *   - Column : 옵션 변경이 필요할 때
    *   - GeneratedValue : PK 생성규칙
    *       -> Table, Sequence, identity ...
    *
    * Lombok Annotation
    *   - Getter
    *       -> Setter는 추가 X
    *       -> 필드의 값 변경이 필요하다면 명확한 목적, 의도를 나타낼 수 있는 메소드를 새롭게 추가한다. ex) Posts(...arg)
    *   - NoArgsConstructor : 기본 생성자
    *   - Builder : 객체에 값을 채워준다. 생성자 선언시 생성자에 포함된 필드만 포함
    *       -> Setter 대신 Builder를 사용하여 값 변경의 명확한 목적을 나타내자
    * */
}
