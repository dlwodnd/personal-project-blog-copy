package com.project.t_story_copy_project.commom.repository;

import com.project.t_story_copy_project.commom.entity.BlogEntity;
import com.project.t_story_copy_project.commom.entity.CatEntity;
import com.project.t_story_copy_project.commom.entity.FeedEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedRepository extends JpaRepository<FeedEntity, Long> {
    Long countByCatEntity(CatEntity catEntity);
    Long countAllByBlogEntity(BlogEntity blogEntity);
    Optional<FeedEntity> findAllByComplete(Long complete);
    Page<FeedEntity> findAllByCompleteAndFeedPrivateOrderByCreatedAtDesc(Long complete, Long feedPrivate, Pageable pageable);
    Page<FeedEntity> findAllByCompleteAndFeedPrivateAndBlogEntityInOrderByCreatedAtDesc(Long complete, Long feedPrivate, List<BlogEntity> blogEntityList, Pageable pageable);
}
