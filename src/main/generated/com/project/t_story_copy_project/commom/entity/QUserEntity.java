package com.project.t_story_copy_project.commom.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = -845906307L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final com.project.t_story_copy_project.commom.entity.base.QBaseEntity _super = new com.project.t_story_copy_project.commom.entity.base.QBaseEntity(this);

    public final ListPath<BlogEntity, QBlogEntity> blogEntityList = this.<BlogEntity, QBlogEntity>createList("blogEntityList", BlogEntity.class, QBlogEntity.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath nickname = createString("nickname");

    public final EnumPath<com.project.t_story_copy_project.commom.entity.jpa_enum.UserRoleEnum> role = createEnum("role", com.project.t_story_copy_project.commom.entity.jpa_enum.UserRoleEnum.class);

    public final EnumPath<com.project.t_story_copy_project.commom.entity.jpa_enum.SocialEnum> socialType = createEnum("socialType", com.project.t_story_copy_project.commom.entity.jpa_enum.SocialEnum.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath userEmail = createString("userEmail");

    public final StringPath userName = createString("userName");

    public final StringPath userPic = createString("userPic");

    public final NumberPath<Long> userPk = createNumber("userPk", Long.class);

    public final StringPath userPw = createString("userPw");

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

