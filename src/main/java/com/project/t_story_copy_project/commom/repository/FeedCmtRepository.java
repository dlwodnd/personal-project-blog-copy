package com.project.t_story_copy_project.commom.repository;

import com.project.t_story_copy_project.commom.entity.FeedCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedCmtRepository extends JpaRepository<FeedCommentEntity, Long> {
}
