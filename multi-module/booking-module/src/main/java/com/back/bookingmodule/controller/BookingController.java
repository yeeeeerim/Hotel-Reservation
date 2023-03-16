package com.back.bookingmodule.controller;

import com.back.bookingmodule.data.BookingDTO;
import com.back.bookingmodule.domain.Booking;
import com.back.bookingmodule.repository.BookingRepository;
import com.back.bookingmodule.service.BookingService;
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
    private final BookingRepository bookingRepository;

    @PostMapping("/bookings")
    public ResponseEntity<BookingDTO> bookingSave(@RequestBody BookingDTO bookingDTO){
        Booking createBooking = bookingService.bookingSave(bookingDTO);
        return ResponseEntity.ok(bookingDTO);
    }

    @GetMapping("/list") //예약 리스트 전부 불러오기
    public List<Booking> bookingList(){
        return bookingService.getBooking();
    }
    @GetMapping("/id") //id로 예약정보 한개 찾기
    public Optional<BookingDTO> findBooking(BookingDTO dto){
        return bookingService.findById(dto.toEntity().getId());
    }

//    @GetMapping("/{id}") // 수정
//    public ResponseEntity<Void> updateBooking(@PathVariable Long id, @RequestBody Booking booking){
//        bookingService.updateBooking(booking.getCheckIn(), booking.getCheckOut(), booking.getMember(), id);
//        return ResponseEntity.ok().build(); //
//    }

//    @GetMapping("/{id}") // 삭제
//    public ResponseEntity<?> deleteBooking(@PathVariable("id") Long id) {
//        Optional<Booking> reservation = bookingRepository.findById(id);
//        if (!reservation.isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        bookingRepository.deleteById(id);
//        return ResponseEntity.ok().build();
//    }
}
