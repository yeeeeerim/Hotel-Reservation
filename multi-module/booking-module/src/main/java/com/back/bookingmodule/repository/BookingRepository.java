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
<<<<<<< HEAD
    @Modifying
    @Query("update Booking b set b.checkIn = :checkIn, b.checkOut = :checkOut where b.id = :id")
    void updateBooking(@Param("id") Long id, @Param("checkIn")String checkIn, @Param("checkOut")String checkOut);
=======
    @Query("update Booking b " +
            "set b.id = :id, b.checkIn = :checkIn, b.checkOut = :checkOut " +
            "where b.member.email = :email")
    void updateBooking(@Param("member")Member member, @Param("id") Long id, @Param("checkIn")String checkIn, @Param("checkOut")String checkOut);
>>>>>>> d9e1a2d16757b4731bb10911a32e4d17cfdd989c
}
