package com.project.t_story_copy_project.commom.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCatEntity is a Querydsl query type for CatEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCatEntity extends EntityPathBase<CatEntity> {

    private static final long serialVersionUID = 452228746L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCatEntity catEntity1 = new QCatEntity("catEntity1");

    public final QBlogEntity blogEntity;

    public final QCatEntity catEntity;

    public final ListPath<CatEntity, QCatEntity> catEntityList = this.<CatEntity, QCatEntity>createList("catEntityList", CatEntity.class, QCatEntity.class, PathInits.DIRECT2);

    public final StringPath catNm = createString("catNm");

    public final NumberPath<Long> catPk = createNumber("catPk", Long.class);

    public QCatEntity(String variable) {
        this(CatEntity.class, forVariable(variable), INITS);
    }

    public QCatEntity(Path<? extends CatEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCatEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCatEntity(PathMetadata metadata, PathInits inits) {
        this(CatEntity.class, metadata, inits);
    }

    public QCatEntity(Class<? extends CatEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.blogEntity = inits.isInitialized("blogEntity") ? new QBlogEntity(forProperty("blogEntity"), inits.get("blogEntity")) : null;
        this.catEntity = inits.isInitialized("catEntity") ? new QCatEntity(forProperty("catEntity"), inits.get("catEntity")) : null;
    }

}

