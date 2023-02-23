package org.hotel.back.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRating is a Querydsl query type for Rating
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRating extends EntityPathBase<Rating> {

    private static final long serialVersionUID = -1038728284L;

    public static final QRating rating = new QRating("rating");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Integer> hotelId = createNumber("hotelId", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath ratingContent = createString("ratingContent");

    public final NumberPath<Integer> ratingId = createNumber("ratingId", Integer.class);

    public QRating(String variable) {
        super(Rating.class, forVariable(variable));
    }

    public QRating(Path<? extends Rating> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRating(PathMetadata metadata) {
        super(Rating.class, metadata);
    }

}

