package com.project.t_story_copy_project.commom.entity.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedFavComposite implements Serializable {
    private Long userPk;
    private Long feedPk;
}
