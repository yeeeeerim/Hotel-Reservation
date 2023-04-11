package org.hotel.back.repository.custom;

import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository {
    public Optional<List<Review>> getReviewInfo(String email);



    public Hotel getJPQLHotelInfo(String email);
}
