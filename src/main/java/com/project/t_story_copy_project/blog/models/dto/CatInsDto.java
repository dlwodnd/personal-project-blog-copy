package com.project.t_story_copy_project.blog.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CatInsDto {
    private Long blogPk;
    private List<CatInfoDto> catInfoList;
}
