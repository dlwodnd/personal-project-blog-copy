package com.project.t_story_copy_project.feed.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FeedCmtInsDto {
    private Long feedPk;
    private String cmtNm;
    private String cmtPw;
    private String cmt;
    private Long cmtPrivate;
}
