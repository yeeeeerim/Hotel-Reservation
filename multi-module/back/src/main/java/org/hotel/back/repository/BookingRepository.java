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
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {


    @Transactional
    @Modifying
    @Query("update Booking b set b.checkIn = :checkIn, b.checkOut = :checkOut where b.id = :id")
    void updateBooking(@Param("checkIn") LocalDateTime checkIn, @Param("checkOut") LocalDateTime checkOut, @Param("id") Long id);



    @Query("select b from Booking b join fetch b.room where b.memberEmail = :email and b.deleted = false")
     List<Booking> findByEmail(@Param("email") String email);


    @Query("select b from Booking b join fetch b.member join fetch b.room where b.id = :id")
    Optional<Booking> findByIdWithMember(@Param("id") Long id);



}

