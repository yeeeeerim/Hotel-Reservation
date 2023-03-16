package org.hotel.back.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hotel.back.config.exception.BookingException;
import org.hotel.back.data.dto.BookingDTO;
import org.hotel.back.data.response.BookingErrorResponse;
import org.hotel.back.domain.*;
import org.hotel.back.service.BookingService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reservation")
@Slf4j
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/booking/availableRooms")
    public List<Room> bookingRead(Model model, @RequestParam LocalDateTime checkIn, @RequestParam LocalDateTime checkOut){
        List<Room> availableRoomList = bookingService.findAvailable(checkIn, checkOut);
        model.addAttribute("roomList", availableRoomList);
        return availableRoomList;
    }

    @GetMapping("/booking/date")
    public String selectDate(){
        return "booking/dateSelecte";
    }
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
    public BookingDTO findBookingId(@RequestParam("id") Long id){
        BookingDTO dto = bookingService.findById(id);
        return dto;
    }

    @PutMapping("/modify/{id}") // 수정
    public Boolean updateBooking(@RequestBody BookingDTO bookingDTO, @AuthenticationPrincipal Principal principal) {
        if (principal.getName().equals(bookingDTO.getMemberEmail())) {
            bookingService.updateBooking(bookingDTO.getCheckIn(), bookingDTO.getCheckOut(), bookingDTO.toEntity().getId());
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