package com.project.t_story_copy_project.commom.repository;

import com.project.t_story_copy_project.commom.entity.FeedEntity;
import com.project.t_story_copy_project.commom.entity.HashTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HashtagRepository extends JpaRepository<HashTagEntity, Long> {
    List<HashTagEntity> findTop3ByFeedEntity(FeedEntity feedEntity);
}
