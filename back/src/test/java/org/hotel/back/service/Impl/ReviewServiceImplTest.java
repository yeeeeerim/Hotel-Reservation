package org.hotel.back.service.Impl;

import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.Review;
import org.hotel.back.dto.request.ReviewRequestDTO;
import org.hotel.back.repository.HotelRepository;
import org.hotel.back.repository.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ReviewServiceImplTest {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    HotelRepository hotelRepository;
    @Test
    @DisplayName("리뷰insert")
    Long saveReview() {
        Hotel hotel = hotelRepository.findById((long)5).
                orElseThrow(()->new IllegalArgumentException
                        ("리뷰 쓰기 실패: 해당 호텔이 존재하지 않습니다." + 5));
        ReviewRequestDTO reviewRequestDTO=new ReviewRequestDTO((long)1,"aa",null,null,"aa",null);
        reviewRequestDTO.setHotel(hotel);//해당 호텔 dto에 추가하기
        System.out.println(reviewRequestDTO);
        Review review = reviewRequestDTO.toEntity(reviewRequestDTO);
        reviewRepository.save(review);
        System.out.println("aaa================================"+review);
        return reviewRequestDTO.getId();}


}