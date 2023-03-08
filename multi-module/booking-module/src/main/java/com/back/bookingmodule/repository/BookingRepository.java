package com.back.bookingmodule.repository;

import com.back.bookingmodule.domain.Booking;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    @Query("update Booking b set b.checkIn = :checkIn, b.checkOut = :checkOut where b.id = :id")
    void updateBooking(@Param("id") Long id, @Param("checkIn")String checkIn, @Param("checkOut")String checkOut);
}
