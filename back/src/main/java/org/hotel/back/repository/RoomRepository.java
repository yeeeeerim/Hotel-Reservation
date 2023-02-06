package org.hotel.back.repository;

import org.hotel.back.domain.Room;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room,Long> {


    @EntityGraph(attributePaths = {"roomImage"})
    @Query("SELECT r FROM Room r WHERE r.id = :id")
    public Optional<Room> getRoomWithImage(@Param("id") Long id);


    @Query("SELECT DISTINCT r FROM Room  r LEFT JOIN FETCH r.roomImage WHERE r.hotel.id = :id")
    public List<Room> roomListWithImage(@Param("id") Long id);
}
