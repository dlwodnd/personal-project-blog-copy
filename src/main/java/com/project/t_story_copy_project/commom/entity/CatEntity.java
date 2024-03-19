package com.project.t_story_copy_project.commom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_cat")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CatEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long catPk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_pk", referencedColumnName = "blogPk", columnDefinition = "BIGINT UNSIGNED")
    private BlogEntity blogEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "top_cat_pk", referencedColumnName = "catPk", columnDefinition = "BIGINT UNSIGNED")
    private CatEntity catEntity;

    @Column(nullable = false)
    private String catNm;

    @OneToMany(mappedBy = "catEntity", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CatEntity> catEntityList = new ArrayList<>();
}
