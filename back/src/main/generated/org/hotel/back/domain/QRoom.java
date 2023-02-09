package org.hotel.back.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoom is a Querydsl query type for Room
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoom extends EntityPathBase<Room> {

    private static final long serialVersionUID = -613357406L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoom room = new QRoom("room");

    public final StringPath description = createString("description");

    public final QHotel hotel;

    public final NumberPath<Long> hotelId = createNumber("hotelId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath roomClass = createString("roomClass");

    public final SetPath<RoomImage, QRoomImage> roomImage = this.<RoomImage, QRoomImage>createSet("roomImage", RoomImage.class, QRoomImage.class, PathInits.DIRECT2);

    public final StringPath roomLimit = createString("roomLimit");

    public final StringPath roomNumber = createString("roomNumber");

    public final StringPath roomPrice = createString("roomPrice");

    public QRoom(String variable) {
        this(Room.class, forVariable(variable), INITS);
    }

    public QRoom(Path<? extends Room> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoom(PathMetadata metadata, PathInits inits) {
        this(Room.class, metadata, inits);
    }

    public QRoom(Class<? extends Room> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hotel = inits.isInitialized("hotel") ? new QHotel(forProperty("hotel")) : null;
    }

}

