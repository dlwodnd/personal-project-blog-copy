package com.project.t_story_copy_project.feed.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FeedCmtPutDto {
    private Long cmtPk;
    private String cmtPw;
    private String cmt;
    private Long cmtPrivate;
}
