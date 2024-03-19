package com.project.t_story_copy_project.commom.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
//단순히 부모 클래스를 상속 받는 자식 클래스에 매핑 정보만 제공한다.
@EntityListeners(AuditingEntityListener.class)
public class CreatedAtBaseEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
