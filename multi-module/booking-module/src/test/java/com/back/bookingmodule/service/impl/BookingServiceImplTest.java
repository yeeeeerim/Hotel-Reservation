package com.back.bookingmodule.service.impl;

import com.back.bookingmodule.service.BookingService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookingServiceImplTest {
    @Autowired
    BookingService bookingService;

    @Test
    @DisplayName("예약서비스를 테스트하기 위함..")
    void serviceTest(){
        bookingService.bookingChangeInAndOut();
    }

}