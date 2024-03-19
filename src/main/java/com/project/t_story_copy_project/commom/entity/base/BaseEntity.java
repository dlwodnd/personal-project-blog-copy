package com.project.t_story_copy_project.commom.entity.base;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity extends CreatedAtBaseEntity {
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
