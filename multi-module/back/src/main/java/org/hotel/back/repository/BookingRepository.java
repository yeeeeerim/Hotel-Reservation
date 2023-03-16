package org.hotel.back.repository;

import org.hotel.back.domain.Booking;
import org.hotel.back.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT r FROM Room r WHERE r.id NOT IN (SELECT b FROM Booking b WHERE b.checkIn <= :checkOut AND b.check_out >= :checkIn)")
    List<Room> getNotReservation(@Param("checkIn")String checkIn, @Param("checkOut")String checkOut);
}
