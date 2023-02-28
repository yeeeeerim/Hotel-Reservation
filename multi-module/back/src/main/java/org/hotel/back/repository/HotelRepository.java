package org.hotel.back.repository;

import org.hotel.back.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel,Long> {

    @Query("select h, avg(coalesce(r.rating,0))" +
            " from Hotel h " +
            "left outer join HotelImage hi on hi.hotel = h " +
            " left outer join Review r on r.hotel = h " +
            " where h.id = :id ")
    List<Object[]> getHotelWithAll(@Param("id")Long id);


    List<Hotel> findByHotelNameContaining(String keyword);
}
