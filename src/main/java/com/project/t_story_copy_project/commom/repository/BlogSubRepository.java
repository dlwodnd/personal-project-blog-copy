package com.project.t_story_copy_project.commom.repository;

import com.project.t_story_copy_project.commom.entity.BlogSubEntity;
import com.project.t_story_copy_project.commom.entity.embeddable.BlogSubComposite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogSubRepository extends JpaRepository<BlogSubEntity, BlogSubComposite>{
}
