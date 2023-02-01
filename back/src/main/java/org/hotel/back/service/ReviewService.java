package org.hotel.back.service;

import org.hotel.back.domain.Hotel;
import org.hotel.back.dto.request.HotelRequestDTO;
import org.hotel.back.dto.request.ReviewRequestDTO;
import org.hotel.back.dto.response.ReviewResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    public ReviewResponseDTO readReview(Long id);

    public List<ReviewResponseDTO> findReview(Long id);

    public Long saveReview(Long id, ReviewRequestDTO reviewRequestDTO);

    public Long updateReview(ReviewRequestDTO reviewRequestDTO);

    public boolean deleteReview(Long id);
}
