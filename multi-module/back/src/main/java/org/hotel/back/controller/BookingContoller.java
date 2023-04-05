package org.hotel.back.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hotel.back.data.dto.BookingDTO;
import org.hotel.back.data.dto.MemberDTO;
import org.hotel.back.data.response.RoomDTO;
import org.hotel.back.domain.*;
import org.hotel.back.repository.BookingRepository;
import org.hotel.back.repository.RoomRepository;
import org.hotel.back.service.BookingService;
import org.hotel.back.service.HotelService;
import org.hotel.back.service.MemberService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @PostMapping("/booking/save")
    public Boolean bookingSave(@RequestBody @Valid DataDTO dto
            , BindingResult bindingResult
            , @AuthenticationPrincipal MemberDTO memberDTO
                               ){

        if(memberDTO == null) {//저장하지 못하게

        }

        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.error("ERROR Message {}  ", objectError.getDefaultMessage());
            });
        }else{
            System.out.println(localDateTimeParser(dto.getCheckIn()));
            System.out.println(localDateTimeParser(dto.getCheckOut()));
        }

        log.info("TIme++>>>>>>> {}",dto);
        return false;

    }



    @NoArgsConstructor
    @Data
    static class DataDTO{


        @Pattern(regexp="^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$",message = "들어온 날짜 형식이 일치하지 않음 yyyy-MM-dd")
        String checkIn;   //yyyy-MM-dd
        @Pattern(regexp="^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$",message = "들어온 날짜 형식이 일치하지 않음 yyyy-MM-dd")
        String checkOut;   //yyyy-MM-dd

    }

    public LocalDateTime localDateTimeParser(String date){
        String dateString = date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate dateTime = LocalDate.parse(dateString, formatter);
        LocalDateTime localDateTimeOut = dateTime.atTime(12,0);

        return localDateTimeOut;
    }


}
