package com.project.t_story_copy_project.feed.models.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CatSimpleVo {
    private Long catPk;
    private String catName;
}
