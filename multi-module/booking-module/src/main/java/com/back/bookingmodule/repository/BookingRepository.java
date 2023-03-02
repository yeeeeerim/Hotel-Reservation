package com.back.bookingmodule.repository;

import com.back.bookingmodule.domain.Booking;
import com.back.bookingmodule.domain.Member;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    @Modifying
    @Transactional
    @Query("update Booking b " +
            "set b.id = :id, b.checkIn = :checkIn, b.checkOut = :checkOut " +
            "where b.member.email = :email")
    void updateBooking(@Param("member")Member member, @Param("id") Long id, @Param("checkIn")String checkIn, @Param("checkOut")String checkOut);
}
