package com.project.t_story_copy_project.blog.models.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatSubInfoVo {
    private Long catPk;
    private Long catSeq;
    private String catName;
    private Long topSeq;
    private Long catOrder;
}
