package org.hotel.back.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHotel is a Querydsl query type for Hotel
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHotel extends EntityPathBase<Hotel> {

    private static final long serialVersionUID = -1843440947L;

    public static final QHotel hotel = new QHotel("hotel");

    public final ListPath<Booking, QBooking> bookingList = this.<Booking, QBooking>createList("bookingList", Booking.class, QBooking.class, PathInits.DIRECT2);

    public final StringPath cityName = createString("cityName");

    public final ListPath<HotelImage, QHotelImage> hotelImages = this.<HotelImage, QHotelImage>createList("hotelImages", HotelImage.class, QHotelImage.class, PathInits.DIRECT2);

    public final StringPath hotelName = createString("hotelName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath latitude = createString("latitude");

    public final StringPath longitude = createString("longitude");

    public final ListPath<Review, QReview> reviews = this.<Review, QReview>createList("reviews", Review.class, QReview.class, PathInits.DIRECT2);

    public final SetPath<Room, QRoom> roomSet = this.<Room, QRoom>createSet("roomSet", Room.class, QRoom.class, PathInits.DIRECT2);

    public final StringPath tellNumber = createString("tellNumber");

    public final StringPath writer = createString("writer");

    public QHotel(String variable) {
        super(Hotel.class, forVariable(variable));
    }

    public QHotel(Path<? extends Hotel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHotel(PathMetadata metadata) {
        super(Hotel.class, metadata);
    }

}

