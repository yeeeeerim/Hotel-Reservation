package com.back.bookingmodule.repository;

import com.back.bookingmodule.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
}
