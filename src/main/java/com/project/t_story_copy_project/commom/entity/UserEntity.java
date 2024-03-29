package com.project.t_story_copy_project.commom.entity;

import com.project.t_story_copy_project.commom.entity.base.BaseEntity;
import com.project.t_story_copy_project.commom.entity.jpa_enum.SocialEnum;
import com.project.t_story_copy_project.commom.entity.jpa_enum.UserRoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_user"
        ,uniqueConstraints = {@UniqueConstraint(
                name = "user_email_social_type_unique",
                columnNames = {"user_email","social_type"}
)})
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPk;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = false)
    private String userName;

    private String userPic;

    @Column(nullable = false)
    private String nickname;

    @ColumnDefault("'NORMAL'")
    @Enumerated(value = EnumType.STRING)
    private SocialEnum socialType;

    @ColumnDefault("'USER'")
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<BlogEntity> blogEntityList = new ArrayList<>();

    public void changeNickname(String newNickname) {
        this.nickname = newNickname;
    }

    public void changeUserPic(String newUserPic) {
        this.userPic = newUserPic;
    }


}
