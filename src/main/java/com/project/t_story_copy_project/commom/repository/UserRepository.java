package com.project.t_story_copy_project.commom.repository;

import com.project.t_story_copy_project.commom.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByNickname(String nickname);
    Optional<UserEntity> findByUserEmail(String email);
}
