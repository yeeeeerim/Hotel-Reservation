package org.hotel.back.service.impl;

import lombok.RequiredArgsConstructor;
import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.Review;
import org.hotel.back.dto.request.ReviewRequestDTO;
import org.hotel.back.dto.response.ReviewResponseDTO;
import org.hotel.back.repository.HotelRepository;
import org.hotel.back.repository.ReviewRepository;
import org.hotel.back.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final HotelRepository hotelRepository;

    @Override
    public Long saveReview(Long id, ReviewRequestDTO reviewRequestDTO) {
        Hotel hotel = hotelRepository.findById(id).
                orElseThrow(()->new IllegalArgumentException
                        ("리뷰 쓰기 실패: 해당 호텔이 존재하지 않습니다." + id));
        reviewRequestDTO.setHotel(hotel);//해당 호텔 dto에 추가하기

        Review review = reviewRequestDTO.toEntity(reviewRequestDTO);
        reviewRepository.save(review);
        System.out.println("aaa================================"+review);
        return reviewRequestDTO.getId();
    }

    @Override
    public ReviewResponseDTO readReview(Long id){

        Review review=reviewRepository.findById
                (id).orElseThrow(RuntimeException::new);
        return new ReviewResponseDTO(review);

    }

    @Override
    public List<ReviewResponseDTO> findReview(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 호텔이 존재하지 않습니다."));
        List<Review> reviews = hotel.getReviews();
        return reviews.stream().map(ReviewResponseDTO::new).collect(Collectors.toList());

    }


    public Long updateReview(ReviewRequestDTO reviewRequestDTO){
        Review review=reviewRepository.findById(reviewRequestDTO.getId())
                .orElseThrow(()-> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. "));
        review.updateReview(reviewRequestDTO.getReviewContent());
        reviewRepository.save(review);
        return review.getHotel().getId();
    }

    public boolean deleteReview(Long id){
        reviewRepository.deleteById(id);
        return true;
    }
}
