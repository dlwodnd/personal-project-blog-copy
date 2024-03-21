package com.project.t_story_copy_project.commom.repository;

import com.project.t_story_copy_project.commom.entity.CatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CatRepository extends JpaRepository<CatEntity, Long>{
    Optional<CatEntity> findBySeq(long seq);
    CatEntity findByCatEntityAndCatOrder(CatEntity catEntity, long catOrder);
}
