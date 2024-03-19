package com.project.t_story_copy_project.commom.entity;

import com.project.t_story_copy_project.commom.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "t_blog")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogEntity extends BaseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogPk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pk", referencedColumnName = "userPk", columnDefinition = "BIGINT UNSIGNED")
    private UserEntity userEntity;

    @Column(nullable = false)
    private String blogTitle;

    private String blogInfo;

    @Column(nullable = false)
    private String blogAddress;

    @Column(nullable = false)
    private String blogPic;

    @ColumnDefault("'0'")
    @Column(nullable = false)
    private Long blogRep;

    @ColumnDefault("'0'")
    @Column(nullable = false)
    private Long cmtOnlyLogin;

    /*@ColumnDefault("'0'")
    @Column(nullable = false)
    private Long guestBookOpen;

    @ColumnDefault("'0'")
    @Column(nullable = false)
    private Long guestBookOnlyLogin;*/


}
