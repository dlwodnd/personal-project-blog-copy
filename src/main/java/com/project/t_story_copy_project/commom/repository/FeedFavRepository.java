package com.project.t_story_copy_project.commom.repository;

import com.project.t_story_copy_project.commom.entity.FeedEntity;
import com.project.t_story_copy_project.commom.entity.FeedFavEntity;
import com.project.t_story_copy_project.commom.entity.embeddable.FeedFavComposite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedFavRepository extends JpaRepository<FeedFavEntity, FeedFavComposite> {
    Long countByFeedEntity(FeedEntity feedEntity);
}
