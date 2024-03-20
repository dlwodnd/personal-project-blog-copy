package com.project.t_story_copy_project.blog.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlogModifyDto {
    private long blogPk;
    private String blogTitle;
    private String blogInfo;
    private String blogNickname;
}
