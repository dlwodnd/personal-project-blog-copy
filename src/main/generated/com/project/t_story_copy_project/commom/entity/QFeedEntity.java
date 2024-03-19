package com.project.t_story_copy_project.commom.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFeedEntity is a Querydsl query type for FeedEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFeedEntity extends EntityPathBase<FeedEntity> {

    private static final long serialVersionUID = 2096205328L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFeedEntity feedEntity = new QFeedEntity("feedEntity");

    public final com.project.t_story_copy_project.commom.entity.base.QBaseEntity _super = new com.project.t_story_copy_project.commom.entity.base.QBaseEntity(this);

    public final QCatEntity catEntity;

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final SetPath<FeedCommentEntity, QFeedCommentEntity> feedCommentEntityList = this.<FeedCommentEntity, QFeedCommentEntity>createSet("feedCommentEntityList", FeedCommentEntity.class, QFeedCommentEntity.class, PathInits.DIRECT2);

    public final ListPath<FeedPicsEntity, QFeedPicsEntity> feedPicsEntityList = this.<FeedPicsEntity, QFeedPicsEntity>createList("feedPicsEntityList", FeedPicsEntity.class, QFeedPicsEntity.class, PathInits.DIRECT2);

    public final NumberPath<Long> feedPk = createNumber("feedPk", Long.class);

    public final StringPath feedPrivate = createString("feedPrivate");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUserEntity userEntity;

    public final NumberPath<Long> viewCount = createNumber("viewCount", Long.class);

    public QFeedEntity(String variable) {
        this(FeedEntity.class, forVariable(variable), INITS);
    }

    public QFeedEntity(Path<? extends FeedEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFeedEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFeedEntity(PathMetadata metadata, PathInits inits) {
        this(FeedEntity.class, metadata, inits);
    }

    public QFeedEntity(Class<? extends FeedEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.catEntity = inits.isInitialized("catEntity") ? new QCatEntity(forProperty("catEntity"), inits.get("catEntity")) : null;
        this.userEntity = inits.isInitialized("userEntity") ? new QUserEntity(forProperty("userEntity")) : null;
    }

}

