package com.project.t_story_copy_project.commom.entity;

import com.project.t_story_copy_project.blog.models.dto.BlogModifyDto;
import com.project.t_story_copy_project.commom.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "blogEntity"
            , fetch = FetchType.LAZY
            , cascade = CascadeType.PERSIST
            , orphanRemoval = true)
    private List<CatEntity> catEntityList = new ArrayList<>();

    public void modifyCatEntityList(List<CatEntity> catEntityList){
        this.catEntityList.clear();
        this.catEntityList.addAll(catEntityList);
    }

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
    public long cmtAccess(){
        if (this.cmtOnlyLogin == 1L){
            this.cmtOnlyLogin = 0L;
            return 2;
        }
        this.blogRep = 1L;
        return 1;
    }
}
