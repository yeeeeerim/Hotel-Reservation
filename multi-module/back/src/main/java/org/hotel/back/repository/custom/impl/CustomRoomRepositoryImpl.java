package org.hotel.back.repository.custom.impl;

import com.querydsl.jpa.JPQLQuery;
import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.QHotel;
import org.hotel.back.domain.Room;
import org.hotel.back.repository.custom.CustomRoomRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public class CustomRoomRepositoryImpl extends QuerydslRepositorySupport  implements CustomRoomRepository {


    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * .
     */
    public CustomRoomRepositoryImpl() {
        super(Room.class);
    }

    public boolean checkingWriter(Long hotelId, String writer){

        QHotel hotel = QHotel.hotel;

        JPQLQuery<Hotel> query = from(hotel);

        Hotel resp = query.where(
                hotel.id.eq(hotelId).and(hotel.writer.eq(writer))
        ).fetchFirst(); //LIMIT 1

        return resp != null;
    }
}
