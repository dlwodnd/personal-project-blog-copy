package com.project.t_story_copy_project.commom.entity.embeddable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlogSubComposite is a Querydsl query type for BlogSubComposite
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBlogSubComposite extends BeanPath<BlogSubComposite> {

    private static final long serialVersionUID = -605931421L;

    public static final QBlogSubComposite blogSubComposite = new QBlogSubComposite("blogSubComposite");

    public final NumberPath<Long> blogPk = createNumber("blogPk", Long.class);

    public final NumberPath<Long> userPk = createNumber("userPk", Long.class);

    public QBlogSubComposite(String variable) {
        super(BlogSubComposite.class, forVariable(variable));
    }

    public QBlogSubComposite(Path<? extends BlogSubComposite> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlogSubComposite(PathMetadata metadata) {
        super(BlogSubComposite.class, metadata);
    }

}

