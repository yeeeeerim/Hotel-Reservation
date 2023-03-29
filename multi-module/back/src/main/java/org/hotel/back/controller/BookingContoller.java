package org.hotel.back.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hotel.back.data.response.RoomDTO;
import org.hotel.back.domain.*;
import org.hotel.back.repository.BookingRepository;
import org.hotel.back.repository.RoomRepository;
import org.hotel.back.service.BookingService;
import org.hotel.back.service.HotelService;
import org.hotel.back.service.MemberService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking")
@Slf4j
public class BookingContoller {
    private final BookingService bookingService;

    /**
     * 예약 가능한 방 불러오기
     * */
    @GetMapping("/available")
    public ResponseEntity<List<RoomDTO>> bookingRead(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkIn,
                                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkOut){

        LocalDateTime localDateTimeIn = checkIn.atTime(12,0);
        LocalDateTime localDateTimeOut = checkOut.atTime(12,0);
        Date checkInDate = Date.from(checkIn.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date checkOutDate = Date.from(checkOut.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<RoomDTO> availableRoomList = bookingService.findAvailable(checkIn, checkOut);
        System.out.println(availableRoomList.get(0));

        return ResponseEntity.ok(availableRoomList);
    }

    /**
     *
     * */

}
