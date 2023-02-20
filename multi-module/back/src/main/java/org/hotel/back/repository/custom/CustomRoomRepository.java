package org.hotel.back.repository.custom;

import org.hotel.back.domain.Hotel;

import java.util.Optional;

public interface CustomRoomRepository {


    /**
     * @param hotelId 룸에서 넘겨받은 호텔 아이디,
     * @param writer 현재사용자
     * */
    public boolean checkingWriter(Long hotelId, String writer);
}
