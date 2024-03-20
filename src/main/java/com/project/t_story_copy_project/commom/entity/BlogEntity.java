package com.project.t_story_copy_project.commom.entity;

import com.project.t_story_copy_project.blog.models.dto.BlogModifyDto;
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

    @Column(nullable = false,updatable = false,unique = true)
    private String blogAddress;

    private String blogPic;

    @Column(nullable = false)
    private String blogNickname;

    @ColumnDefault("'0'")
    private Long blogRep;

    @ColumnDefault("'0'")
    private Long cmtOnlyLogin;

    /*@ColumnDefault("'0'")
    @Column(nullable = false)
    private Long guestBookOpen;

    @ColumnDefault("'0'")
    @Column(nullable = false)
    private Long guestBookOnlyLogin;*/

    public void changeBlogPic (String saveFileName) {
        this.blogPic = saveFileName;
    }
    public void modifyBlogInfo(BlogModifyDto dto){
        if(!dto.getBlogInfo().isEmpty()){
            this.blogInfo = dto.getBlogInfo();
        }
        if (!dto.getBlogNickname().isEmpty()){
            this.blogNickname = dto.getBlogNickname();
        }
        if (!dto.getBlogTitle().isEmpty()){
            this.blogTitle = dto.getBlogTitle();
        }
    }
}
