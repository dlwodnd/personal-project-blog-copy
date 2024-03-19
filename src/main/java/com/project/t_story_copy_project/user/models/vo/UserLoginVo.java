package com.project.t_story_copy_project.user.models.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVo {
    private long userPk;
    private String email;
    private String nickname;
    private String accessToken;
}
