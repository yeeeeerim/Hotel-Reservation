package com.back.bookingmodule.service.impl;

import com.back.bookingmodule.data.BookingDTO;
import com.back.bookingmodule.domain.Status;
import com.back.bookingmodule.service.BookingService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class BookingServiceImplTest {
    @Autowired
    BookingService bookingService;

    @BeforeEach
    @Test
    static BookingDTO createDTO(){
        BookingDTO dto = new BookingDTO("2022.03.10", "2022.03.12", Status.UNAVAILABLE, "1234@gmail.com", LocalDateTime.now(), LocalDateTime.now());
        return dto;

    }


    @Test
    @DisplayName("예약서비스를 테스트하기 위함..")
    void serviceTest(){
<<<<<<< HEAD
        bookingService.bookingSave(createDTO());
        bookingService.getBooking();
=======
        //bookingService.bookingChangeInAndOut();
>>>>>>> d9e1a2d16757b4731bb10911a32e4d17cfdd989c
    }

}