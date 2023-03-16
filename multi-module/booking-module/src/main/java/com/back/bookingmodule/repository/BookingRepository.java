package com.back.bookingmodule.repository;

import com.back.bookingmodule.domain.Booking;
import com.back.bookingmodule.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    @Modifying
    @Transactional
    @Query("update Booking b " +
            "set b.id = :id, b.checkIn = :checkIn, b.checkOut = :checkOut " +
            "where b.member.email = :email")
    void updateBooking(@Param("email")Member member, @Param("id") Long id, @Param("checkIn")String checkIn, @Param("checkOut")String checkOut);

    @Query("select b from Booking b where b.checkOut < :date")
    List<Booking> getBookingByCheckout(@Param("date") LocalDateTime now);
}

