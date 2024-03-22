package com.project.t_story_copy_project.commom.repository;

import com.project.t_story_copy_project.commom.entity.BlogSubEntity;
import com.project.t_story_copy_project.commom.entity.UserEntity;
import com.project.t_story_copy_project.commom.entity.embeddable.BlogSubComposite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogSubRepository extends JpaRepository<BlogSubEntity, BlogSubComposite>{
    List<BlogSubEntity> findAllByUserEntity(UserEntity userEntity);
}
