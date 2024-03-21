package com.project.t_story_copy_project.feed.models.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatSubFeedInfoVo {
    private Long catPk;
    private String catName;
    private Long topSeq;
    private Long feedCount;
}
