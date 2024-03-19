package com.project.t_story_copy_project.commom.entity.embeddable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFeedFavComposite is a Querydsl query type for FeedFavComposite
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QFeedFavComposite extends BeanPath<FeedFavComposite> {

    private static final long serialVersionUID = 1590709732L;

    public static final QFeedFavComposite feedFavComposite = new QFeedFavComposite("feedFavComposite");

    public final NumberPath<Long> feedPk = createNumber("feedPk", Long.class);

    public final NumberPath<Long> userPk = createNumber("userPk", Long.class);

    public QFeedFavComposite(String variable) {
        super(FeedFavComposite.class, forVariable(variable));
    }

    public QFeedFavComposite(Path<? extends FeedFavComposite> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFeedFavComposite(PathMetadata metadata) {
        super(FeedFavComposite.class, metadata);
    }

}

