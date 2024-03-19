package com.project.t_story_copy_project.commom.entity.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BlogSubComposite implements Serializable {
    private Long blogPk;
    private Long userPk;
}
