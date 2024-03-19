package com.project.t_story_copy_project.commom.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFeedPicsEntity is a Querydsl query type for FeedPicsEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFeedPicsEntity extends EntityPathBase<FeedPicsEntity> {

    private static final long serialVersionUID = -492857063L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFeedPicsEntity feedPicsEntity = new QFeedPicsEntity("feedPicsEntity");

    public final QFeedEntity feedEntity;

    public final StringPath feedPic = createString("feedPic");

    public final NumberPath<Long> feedPicsPk = createNumber("feedPicsPk", Long.class);

    public QFeedPicsEntity(String variable) {
        this(FeedPicsEntity.class, forVariable(variable), INITS);
    }

    public QFeedPicsEntity(Path<? extends FeedPicsEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFeedPicsEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFeedPicsEntity(PathMetadata metadata, PathInits inits) {
        this(FeedPicsEntity.class, metadata, inits);
    }

    public QFeedPicsEntity(Class<? extends FeedPicsEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.feedEntity = inits.isInitialized("feedEntity") ? new QFeedEntity(forProperty("feedEntity"), inits.get("feedEntity")) : null;
    }

}

