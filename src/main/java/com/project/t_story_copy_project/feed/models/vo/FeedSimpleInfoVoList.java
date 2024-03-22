package com.project.t_story_copy_project.feed.models.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedSimpleInfoVoList {
    private Long maxPage;
    private Boolean hasNext;
    private Boolean hasPrevious;
    private List<FeedSimpleInfoVo> feedSimpleInfoVoList;
}
