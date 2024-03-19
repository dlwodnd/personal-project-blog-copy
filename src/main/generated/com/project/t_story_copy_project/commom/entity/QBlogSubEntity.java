package com.project.t_story_copy_project.commom.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlogSubEntity is a Querydsl query type for BlogSubEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlogSubEntity extends EntityPathBase<BlogSubEntity> {

    private static final long serialVersionUID = -124771470L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlogSubEntity blogSubEntity = new QBlogSubEntity("blogSubEntity");

    public final com.project.t_story_copy_project.commom.entity.base.QCreatedAtBaseEntity _super = new com.project.t_story_copy_project.commom.entity.base.QCreatedAtBaseEntity(this);

    public final QBlogEntity blogEntity;

    public final com.project.t_story_copy_project.commom.entity.embeddable.QBlogSubComposite blogSubComposite;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final QUserEntity userEntity;

    public QBlogSubEntity(String variable) {
        this(BlogSubEntity.class, forVariable(variable), INITS);
    }

    public QBlogSubEntity(Path<? extends BlogSubEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlogSubEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlogSubEntity(PathMetadata metadata, PathInits inits) {
        this(BlogSubEntity.class, metadata, inits);
    }

    public QBlogSubEntity(Class<? extends BlogSubEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.blogEntity = inits.isInitialized("blogEntity") ? new QBlogEntity(forProperty("blogEntity"), inits.get("blogEntity")) : null;
        this.blogSubComposite = inits.isInitialized("blogSubComposite") ? new com.project.t_story_copy_project.commom.entity.embeddable.QBlogSubComposite(forProperty("blogSubComposite")) : null;
        this.userEntity = inits.isInitialized("userEntity") ? new QUserEntity(forProperty("userEntity")) : null;
    }

}

