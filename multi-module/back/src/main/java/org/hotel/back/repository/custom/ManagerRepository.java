package org.hotel.back.repository.custom;

import org.hotel.back.domain.Hotel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository {
    public Optional<Hotel> getHotelInfo(String email);



    public Hotel getJPQLHotelInfo(String email);
}
