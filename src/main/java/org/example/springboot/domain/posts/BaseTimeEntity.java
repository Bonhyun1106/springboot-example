package org.example.springboot.domain.posts;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modificationDate;

}

/*
    Entity에서 해당 BaseTimeEntity를 상속하여 등록/수정일자를 자동으로 관리할 수 있게 만든다.

    @MappedSuperClass
        : JPA Entity 클래스가 BaseTimeEntity를 상속할 경우 BaseTimeEntity 선언된 필드들도 컬럼으로 인식된다.

    @EntityListeners(AuditingEntityListener.class)
        : JPA Auditing 기능을 포함시킨다.

    @CreateDate
        : Entity가 생성되어 저장될 때 시간이 자동 저장된다.
    @LastModifiedDate
        : 조회한 Entity의 값을 변경할 때 시간이 저장된다.
 */
