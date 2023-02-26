package org.hotel.back.repository.custom;

import org.hotel.back.data.response.HotelResponseDTO;
import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManagerRepository {
    public Hotel getHotelInfo(String email);

    public List<Room> findByRoom(Long id);

    public Hotel getJPQLHotelInfo(String email);


    public HotelResponseDTO findByEmail(String email);
}
