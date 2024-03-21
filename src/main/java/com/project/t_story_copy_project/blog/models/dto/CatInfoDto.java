package com.project.t_story_copy_project.blog.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CatInfoDto {
    private Long catPk;
    private Long catSeq;
    private String catName;
    private Long catOrder;
    private Long topCatSeq;
}
