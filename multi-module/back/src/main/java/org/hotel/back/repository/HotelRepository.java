package org.hotel.back.repository;

import org.hotel.back.domain.Hotel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
    List<Hotel> findByHotelNameContaining(String keyword);
    @Query("select distinct h from Hotel h " +
            "left join fetch h.reviews where h.id = :id")
    Hotel findFetchJoin(@Param("id")Long id);

    @EntityGraph(attributePaths = {"reviews", "hotelImages"})
    @Query("SELECT h FROM Hotel h WHERE h.id = :id")
    Optional<Hotel> getHotelWithReviewsAndImages(@Param("id") Long id);


}
