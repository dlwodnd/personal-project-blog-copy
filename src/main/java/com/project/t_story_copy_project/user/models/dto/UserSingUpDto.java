package com.project.t_story_copy_project.user.models.dto;

import com.project.t_story_copy_project.mail.models.EmailCertificationVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSingUpDto {
    private EmailCertificationVo emailCertificationVo;
    private String password;
    private String name;
    private String nickname;
}
