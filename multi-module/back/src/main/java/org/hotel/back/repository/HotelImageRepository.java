package org.hotel.back.repository;

import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.HotelImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelImageRepository extends JpaRepository<HotelImage, String> {

	List<HotelImage> findHotelImageByHotel_Id(@Param("hotel_id")Long id);
}
