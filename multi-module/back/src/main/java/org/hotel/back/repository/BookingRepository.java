package org.hotel.back.repository;
import org.hotel.back.domain.Booking;
import org.hotel.back.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {


    @Transactional
    @Modifying
    @Query("update Booking b set b.checkIn = :checkIn, b.checkOut = :checkOut where b.id = :id")
    void updateBooking(@Param("checkIn") LocalDateTime checkIn, @Param("checkOut") LocalDateTime checkOut, @Param("id") Long id);

    @Query("SELECT r FROM Room r WHERE r.id NOT IN (SELECT b FROM Booking b WHERE b.checkIn <= :checkOut AND b.checkOut >= :checkIn)")
    List<Room> getNotReservation(@Param("checkIn")LocalDateTime checkIn, @Param("checkOut")LocalDateTime checkOut);
}

