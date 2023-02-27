package org.hotel.back.repository;

import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	@Query("select avg(coalesce(r.rating,0))" +
			" from Review r " +
			" left join Hotel h on r.hotel = h " +
			" where h.id = :id ")
	List<Double> getAVG(Long id);
}
