package com.project.t_story_copy_project.commom.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFeedCommentEntity is a Querydsl query type for FeedCommentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFeedCommentEntity extends EntityPathBase<FeedCommentEntity> {

    private static final long serialVersionUID = -568576939L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFeedCommentEntity feedCommentEntity = new QFeedCommentEntity("feedCommentEntity");

    public final com.project.t_story_copy_project.commom.entity.base.QBaseEntity _super = new com.project.t_story_copy_project.commom.entity.base.QBaseEntity(this);

    public final StringPath cmt = createString("cmt");

    public final StringPath cmtNm = createString("cmtNm");

    public final NumberPath<Long> cmtPk = createNumber("cmtPk", Long.class);

    public final NumberPath<Long> cmtPrivate = createNumber("cmtPrivate", Long.class);

    public final StringPath cmtPw = createString("cmtPw");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final QFeedEntity feedEntity;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUserEntity userEntity;

    public QFeedCommentEntity(String variable) {
        this(FeedCommentEntity.class, forVariable(variable), INITS);
    }

    public QFeedCommentEntity(Path<? extends FeedCommentEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFeedCommentEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFeedCommentEntity(PathMetadata metadata, PathInits inits) {
        this(FeedCommentEntity.class, metadata, inits);
    }

    public QFeedCommentEntity(Class<? extends FeedCommentEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.feedEntity = inits.isInitialized("feedEntity") ? new QFeedEntity(forProperty("feedEntity"), inits.get("feedEntity")) : null;
        this.userEntity = inits.isInitialized("userEntity") ? new QUserEntity(forProperty("userEntity")) : null;
    }

}

