package com.project.t_story_copy_project.blog.models.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogInfoVo {
    private Long blogPk;
    private String blogTitle;
    private String blogAddress;
    private String blogNickname;
    private String blogPic;
    private String blogInfo;

}
