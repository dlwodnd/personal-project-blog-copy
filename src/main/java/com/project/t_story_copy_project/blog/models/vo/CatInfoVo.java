package com.project.t_story_copy_project.blog.models.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatInfoVo {
    private Long catPk;
    private Long catSeq;
    private String catName;
    private Long catOrder;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CatSubInfoVo> catSubInfoVoList;
}
