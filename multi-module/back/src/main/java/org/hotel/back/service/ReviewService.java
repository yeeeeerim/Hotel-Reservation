package org.hotel.back.service;

import org.hotel.back.data.request.ReviewRequestDTO;
import org.hotel.back.data.response.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {
    ReviewResponseDTO readReview(Long id);


	List<ReviewResponseDTO> findReview(Long id);

    Long saveReview(Long hotelId, ReviewRequestDTO reviewRequestDTO);

    Long updateReview(ReviewRequestDTO reviewRequestDTO);

    boolean deleteReview(Long id);

    //Float avgRating(Long id);
}
