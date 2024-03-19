package com.project.t_story_copy_project.commom.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHashTagEntity is a Querydsl query type for HashTagEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHashTagEntity extends EntityPathBase<HashTagEntity> {

    private static final long serialVersionUID = 34111840L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHashTagEntity hashTagEntity = new QHashTagEntity("hashTagEntity");

    public final QFeedEntity feedEntity;

    public final StringPath hashTagNm = createString("hashTagNm");

    public final NumberPath<Long> hashTagPk = createNumber("hashTagPk", Long.class);

    public QHashTagEntity(String variable) {
        this(HashTagEntity.class, forVariable(variable), INITS);
    }

    public QHashTagEntity(Path<? extends HashTagEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHashTagEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHashTagEntity(PathMetadata metadata, PathInits inits) {
        this(HashTagEntity.class, metadata, inits);
    }

    public QHashTagEntity(Class<? extends HashTagEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.feedEntity = inits.isInitialized("feedEntity") ? new QFeedEntity(forProperty("feedEntity"), inits.get("feedEntity")) : null;
    }

}

