package com.back.bookingmodule;

import com.back.bookingmodule.data.BookingDTO;
import com.back.bookingmodule.domain.Status;
import com.back.bookingmodule.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class BookingModuleApplicationTests {
    @Autowired
    BookingService bookingService;

    BookingDTO dto = new BookingDTO("2022.03.10", "2022.03.12", Status.UNAVAILABLE, "1234@gmail.com", LocalDateTime.now(), LocalDateTime.now());

    @Test
    void contextLoads() {
        bookingService.bookingSave(dto);
        bookingService.getBooking();
    }

}
