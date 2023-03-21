package com.back.bookingmodule.service.impl;

import com.back.bookingmodule.domain.Booking;
import com.back.bookingmodule.domain.Member;
import com.back.bookingmodule.repository.BookingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BatchServiceImplTest {

    @Autowired
    private BookingRepository bookingRepository;


    @Test
    @DisplayName("")
    void BatchServiceImplTest() {
        // given
        IntStream.rangeClosed(1,10).forEach(value -> {
            bookingRepository.save(Booking.builder()
                    .member(Member.builder().email("owner@naver.com").build())
                    .checkIn(LocalDateTime.now())
                    .checkOut(LocalDateTime.now().minusDays(value))
                    .build());
        });

        IntStream.rangeClosed(1,10).forEach(value -> {
            bookingRepository.save(Booking.builder()
                    .member(Member.builder().email("owner@naver.com").build())
                    .checkIn(LocalDateTime.now())
                    .checkOut(LocalDateTime.now().plusDays(value))
                    .build());
        });
        // when

        // then
    }
}