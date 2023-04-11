package org.hotel.back.service.Impl;


import org.hotel.back.data.request.ReviewRequestDTO;
import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.Review;
import org.hotel.back.repository.HotelRepository;
import org.hotel.back.repository.ReviewRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import org.hotel.back.repository.HotelRepository;
import org.hotel.back.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReviewServiceImplTest {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    HotelRepository hotelRepository;




}