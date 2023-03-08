package com.back.bookingmodule.controller;

import com.back.bookingmodule.data.BookingDTO;
import com.back.bookingmodule.domain.Booking;
import com.back.bookingmodule.repository.BookingRepository;
import com.back.bookingmodule.service.BookingService;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/save")
    public Boolean bookingSave(@RequestBody BookingDTO bookingDTO){
        try {
            bookingService.bookingSave(bookingDTO);
            return true;
        }catch (Exception e){
            System.out.println("저장실패");
            return false;
        }
    }

    @GetMapping("/list") //예약 리스트 전부 불러오기
    public List<Booking> bookingList(){
        return bookingService.getBooking();
    }
    @GetMapping("/find/{id}") //id로 예약정보 한개 찾기
    public BookingDTO findBooking(@PathVariable("id") Long id){
        BookingDTO dto = bookingService.findById(id);
        return dto;
    }

    @PutMapping("/modify/{id}") // 수정
    public Boolean updateBooking(@PathVariable("id") Long id, @RequestBody BookingDTO bookingDTO){
        try {
            bookingService.updateBooking(bookingDTO.getCheckIn(), bookingDTO.getCheckOut(), bookingDTO.getMemberEmail(), id);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @DeleteMapping("/delete/{id}") // 삭제
    public Boolean deleteBooking(@PathVariable("id") Long id) {
        try {
            bookingService.delete(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
