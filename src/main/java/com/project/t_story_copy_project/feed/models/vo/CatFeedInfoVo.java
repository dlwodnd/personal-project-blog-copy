package com.project.t_story_copy_project.feed.models.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.t_story_copy_project.blog.models.vo.CatSubInfoVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatFeedInfoVo {
    private Long catPk;
    private String catName;
    private Long feedCount;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CatSubFeedInfoVo> catSubInfoVoList;
}
