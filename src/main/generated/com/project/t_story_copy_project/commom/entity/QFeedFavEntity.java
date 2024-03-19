package com.project.t_story_copy_project.commom.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFeedFavEntity is a Querydsl query type for FeedFavEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFeedFavEntity extends EntityPathBase<FeedFavEntity> {

    private static final long serialVersionUID = 1273030033L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFeedFavEntity feedFavEntity = new QFeedFavEntity("feedFavEntity");

    public final com.project.t_story_copy_project.commom.entity.base.QCreatedAtBaseEntity _super = new com.project.t_story_copy_project.commom.entity.base.QCreatedAtBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final QFeedEntity feedEntity;

    public final com.project.t_story_copy_project.commom.entity.embeddable.QFeedFavComposite feedFavComposite;

    public final QUserEntity userEntity;

    public QFeedFavEntity(String variable) {
        this(FeedFavEntity.class, forVariable(variable), INITS);
    }

    public QFeedFavEntity(Path<? extends FeedFavEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFeedFavEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFeedFavEntity(PathMetadata metadata, PathInits inits) {
        this(FeedFavEntity.class, metadata, inits);
    }

    public QFeedFavEntity(Class<? extends FeedFavEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.feedEntity = inits.isInitialized("feedEntity") ? new QFeedEntity(forProperty("feedEntity"), inits.get("feedEntity")) : null;
        this.feedFavComposite = inits.isInitialized("feedFavComposite") ? new com.project.t_story_copy_project.commom.entity.embeddable.QFeedFavComposite(forProperty("feedFavComposite")) : null;
        this.userEntity = inits.isInitialized("userEntity") ? new QUserEntity(forProperty("userEntity")) : null;
    }

}

