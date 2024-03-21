package com.project.t_story_copy_project.feed.models.dto;

import com.project.t_story_copy_project.feed.models.vo.HashTagInfoVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedInsDto {
    private Long feedPk;
    private Long blogPk;
    private Long catPk;
    private String title;
    private String content;
    private Long feedPrivate;
    private List<HashTagInfoVo> hashTagList;
}
