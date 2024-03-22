package com.project.t_story_copy_project.feed.models.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CatVo {
    private Long feedCount;
    private String catAll;
    private List<CatFeedInfoVo> catFeedInfoVoList;
}
