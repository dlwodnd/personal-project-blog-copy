package com.project.t_story_copy_project.commom.entity;

import com.project.t_story_copy_project.commom.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedEntity extends BaseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedPk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pk", referencedColumnName = "userPk", columnDefinition = "BIGINT UNSIGNED")
    private UserEntity userEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_pk", referencedColumnName = "catPk", columnDefinition = "BIGINT UNSIGNED")
    private CatEntity catEntity;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long viewCount;

    @Column(nullable = false)
    private String feedPrivate;

    @OneToMany(mappedBy = "feedEntity", fetch = FetchType.LAZY,cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<FeedPicsEntity> feedPicsEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "feedEntity", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<FeedCommentEntity> feedCommentEntityList = new HashSet<>();
}
