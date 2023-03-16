package com.back.bookingmodule.repository;

import com.back.bookingmodule.domain.booking.Booking;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    @Transactional
    @Modifying
    @Query("update Booking b set b.checkIn = :checkIn, b.checkOut = :checkOut where b.id = :id")
    void updateBooking(@Param("id") Long id, @Param("checkIn")String checkIn, @Param("checkOut")String checkOut);
}
