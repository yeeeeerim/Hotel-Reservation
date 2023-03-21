package com.back.bookingmodule.repository;



import com.back.bookingmodule.domain.booking.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {


    @Query("select b from Booking b where b.checkOut < :date")
    List<Booking> getBookingByCheckout(@Param("date") LocalDateTime now);


}

