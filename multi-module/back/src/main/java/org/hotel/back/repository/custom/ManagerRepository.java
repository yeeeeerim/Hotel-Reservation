package org.hotel.back.repository.custom;

import org.hotel.back.domain.Hotel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManagerRepository {
    public Hotel getHotelInfo(String email);



    public Hotel getJPQLHotelInfo(String email);
}
