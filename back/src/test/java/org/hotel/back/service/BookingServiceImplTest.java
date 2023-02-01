package org.hotel.back.service;

import org.hotel.back.data.request.BookingRequestDTO;
import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.Member;
import org.hotel.back.repository.BookingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookingServiceImplTest {

    @Autowired
    BookingService bookingService;
    @Autowired
    private BookingRepository bookingRepository;

    @Test
    @Transactional
    @DisplayName("CRUD 테스트")
    void test() {
        /*Hotel hotel = Hotel.builder()
                .hotelName("how1")
                .cityName("city1")
                .tellNumber("210-010-010")
                .latitude("3203")
                .longitude("3230")
                .build();
        Member member = Member.builder()
                .memberEmail("test1")
                .memberPassword("test1")
                .memberTellNumber("011-0202-0202")
                .memberGender("woman")
                .memberNickname("mic")
                .build();
        BookingRequestDTO bookingRequestDTO = BookingRequestDTO.builder()
                .hotel(hotel)
                .member(member)
                .build();*/

/*        BookingRequestDTO brt = BookingRequestDTO.builder()
                .bookingId(20L)
                .hotel(hotel)
                .member(member)
                .build();*/

/*        System.out.println("====================");
        System.out.println("저장 테스트");
        bookingService.save(bookingRequestDTO);
        System.out.println(bookingRequestDTO.getBookingId());*/

/*        System.out.println(bookingService.findAll());
        System.out.println("====================");
        System.out.println("조회 테스트");
        System.out.println(bookingService.read(2L));*/

/*
        System.out.println("====================");
        System.out.println("수정 테스트");
        System.out.println(bookingService.modify(brt));
        System.out.println(bookingRequestDTO.getCheckIn() + " \n" +
                bookingRequestDTO.getCheck_out());

        System.out.println("====================");
        System.out.println("삭제 테스트");
        System.out.println(bookingService.delete(1L));
*/

    }


}