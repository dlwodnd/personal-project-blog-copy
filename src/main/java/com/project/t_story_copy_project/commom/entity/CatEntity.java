package com.project.t_story_copy_project.commom.entity;

import com.project.t_story_copy_project.blog.models.dto.CatInfoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_cat", uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_order_cat",
                columnNames = {"top_cat_seq", "cat_order"}
        ),
        @UniqueConstraint(
                name = "uk_seq_blog_pk",
                columnNames = {"seq", "blog_pk"}
        )
})
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CatEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long catPk;

    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_pk", referencedColumnName = "blogPk", columnDefinition = "BIGINT UNSIGNED")
    private BlogEntity blogEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "top_cat_seq", referencedColumnName = "seq", columnDefinition = "BIGINT UNSIGNED")
    private CatEntity catEntity;

    @Column(nullable = false)
    private String catNm;

    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long catOrder;

    @OneToMany(mappedBy = "catEntity", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<CatEntity> catEntityList = new ArrayList<>();

    public void editCatEntity(CatInfoDto dto,CatEntity catEntity) {
        this.seq = dto.getCatSeq();
        this.catNm = dto.getCatName();
        this.catOrder = dto.getCatOrder();
        this.catEntity = catEntity;
    }
}
