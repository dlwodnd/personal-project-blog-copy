package com.project.t_story_copy_project.commom.repository;

import com.project.t_story_copy_project.commom.entity.CatEntity;
import com.project.t_story_copy_project.commom.entity.FeedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedRepository extends JpaRepository<FeedEntity, Long> {
    long countByCatEntity(CatEntity catEntity);
    Optional<FeedEntity> findAllByComplete(Long complete);
}
