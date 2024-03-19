package com.project.t_story_copy_project.commom.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlogEntity is a Querydsl query type for BlogEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlogEntity extends EntityPathBase<BlogEntity> {

    private static final long serialVersionUID = 1730757908L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlogEntity blogEntity = new QBlogEntity("blogEntity");

    public final com.project.t_story_copy_project.commom.entity.base.QBaseEntity _super = new com.project.t_story_copy_project.commom.entity.base.QBaseEntity(this);

    public final StringPath blogAddress = createString("blogAddress");

    public final StringPath blogPic = createString("blogPic");

    public final NumberPath<Long> blogPk = createNumber("blogPk", Long.class);

    public final NumberPath<Long> blogRef = createNumber("blogRef", Long.class);

    public final StringPath blogTitle = createString("blogTitle");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUserEntity userEntity;

    public QBlogEntity(String variable) {
        this(BlogEntity.class, forVariable(variable), INITS);
    }

    public QBlogEntity(Path<? extends BlogEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlogEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlogEntity(PathMetadata metadata, PathInits inits) {
        this(BlogEntity.class, metadata, inits);
    }

    public QBlogEntity(Class<? extends BlogEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userEntity = inits.isInitialized("userEntity") ? new QUserEntity(forProperty("userEntity")) : null;
    }

}

