package com.back.bookingmodule.repository;

import com.back.bookingmodule.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
}
