package com.back.bookingmodule.controller;

import com.back.bookingmodule.config.exception.BookingException;
import com.back.bookingmodule.data.BookingDTO;
import com.back.bookingmodule.data.Request.DateRequest;
import com.back.bookingmodule.data.Response.BookingErrorResponse;
import com.back.bookingmodule.domain.booking.Booking;
import com.back.bookingmodule.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/save")
    public Boolean bookingSave(@RequestBody BookingDTO bookingDTO){
        bookingService.bookingSave(bookingDTO);
        return true;
    }


    @GetMapping("/list") //예약 리스트 전부 불러오기
    public List<Booking> bookingList(){
        return bookingService.getBooking();
    }
    @GetMapping("/find/{id}") //id로 예약정보 한개 찾기
    public BookingDTO findBooking(@RequestParam("id") Long id){
        BookingDTO dto = bookingService.findById(id);
        return dto;
    }

    @PutMapping("/modify/{id}") // 수정
    public Boolean updateBooking(@RequestBody BookingDTO bookingDTO, @AuthenticationPrincipal Principal principal) {
        if (principal.getName().equals(bookingDTO.getMemberEmail())) {
            bookingService.updateBooking(bookingDTO.getCheckIn(), bookingDTO.getCheckOut(), bookingDTO.getMemberEmail(), bookingDTO.toEntity().getId());
            return true;
        } else if (principal.getName().equals(null)) {
            throw new NullPointerException();
        }else return false;
    }

    @DeleteMapping("/delete/{id}") // 삭제
    public Boolean deleteBooking(@PathVariable("id") Long id){
            bookingService.delete(id);
            return true;
    }

    @ExceptionHandler(Exception.class)
    public BookingErrorResponse handleException(BookingException e){
        return BookingErrorResponse.builder()
                .errorCode(e.getBookingErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }
}
