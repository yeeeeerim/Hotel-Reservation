package com.back.bookingmodule.repository;



import com.back.bookingmodule.domain.booking.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {


    @Query("select b from Booking b where b.checkOut < :date")
    List<Booking> getBookingByCheckout(@Param("date") LocalDateTime now);


    @Modifying
    @Transactional
    @Query("update Booking b set b.deleted = true where b.id = :id")
    public  void updateDelete(@Param("id") Long id);

}

