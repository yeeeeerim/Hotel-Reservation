package org.hotel.back.repository;

import org.apache.tomcat.jni.Local;
import org.hotel.back.data.response.RoomDTO;
import org.hotel.back.domain.Room;
import org.hotel.back.repository.custom.CustomRoomRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room,Long>,CustomRoomRepository{


    @EntityGraph(attributePaths = {"roomImage"})
    @Query("SELECT r FROM Room r WHERE r.id = :id")
    public Optional<Room> getRoomWithImage(@Param("id") Long id);

    @EntityGraph(attributePaths = {"roomImage"})
    @Query("SELECT r FROM Room r WHERE r.id = :id")
    public Optional<Room> getRoomWithImageOne(@Param("id") Long id);


    @Query("SELECT DISTINCT r FROM Room  r LEFT JOIN FETCH r.roomImage WHERE r.hotel.id = :id")
    public List<Room> roomListWithImage(@Param("id") Long id);

    @Query("SELECT r FROM Room r WHERE r.id NOT IN (SELECT b FROM Booking b WHERE b.checkIn <= :checkOut AND b.check_out >= :checkIn)")
    List<Room> findAvailableRooms(@Param("checkIn") Date checkIn, @Param("checkOut") Date checkOut);


}
