package com.project.t_story_copy_project.security;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyPrincipal {
    private long userPk;
    private String role;
}
