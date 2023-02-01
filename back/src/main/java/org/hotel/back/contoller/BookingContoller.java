package org.hotel.back.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hotel.back.data.request.BookingRequestDTO;
import org.hotel.back.data.response.BookingResponseDTO;
import org.hotel.back.repository.BookingRepository;
import org.hotel.back.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@Slf4j
public class BookingContoller {

    private final BookingService bookingService;

    /*view 예약된 정보 조회*//*
    @GetMapping("/booking")
    public String bookingRead(Model model, @RequestParam Long id){
        model.addAttribute("booking",bookingService.read(id));
        return "booking";
    }*/

    /*기능테스트용 예약된 정보 조회*/
    @GetMapping("/bookingRead")
    public ResponseEntity<BookingResponseDTO> bookingRead(@RequestParam Long id){
        return ResponseEntity.ok(bookingService.read(id));

    }



    /*view - 예약하기 버튼으로 수행되는 저장
    * requestDTO의 setter를 통해 바인딩 된 form데이터 저장
    * prg패턴으로 예약완료된 페이지 보여줌. *//*
    @PostMapping("/hotel/detail/save")
    public String bookingSave(BookingRequestDTO bookingRequestDTO){
        bookingService.save(bookingRequestDTO);
        return "redirect:/booking";
    }*/

    /* 기능테스트용 - 예약하기 버튼으로 수행되는 저장
     * requestDTO의 setter를 통해 바인딩 된 form데이터 저장
     * prg패턴으로 예약완료된 페이지 보여줌. */
    @PostMapping("/bookingSave")
    public ResponseEntity<Boolean> bookingSave(@RequestBody BookingRequestDTO bookingRequestDTO){
        try {
            bookingService.save(bookingRequestDTO);
        }catch (Exception e){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

  /*  *//*수정
    * 조회된 예약정보에서 입실 기능으로 수행되는 수정기능
    * 입실 및 퇴실시간 자동등록*//*
    @PostMapping("/booking/modify")
    public String bookingModify(BookingRequestDTO bookingRequestDTO){
        bookingService.modify(bookingRequestDTO);
        return "redirect:/booking";
    }*/

  /*기능테스트용 수정
   * 조회된 예약정보에서 입실 기능으로 수행되는 수정기능
   * 입실 및 퇴실시간 자동등록*/
  @PutMapping("/bookingModify")
  public ResponseEntity<Boolean> bookingModify(@RequestBody BookingRequestDTO bookingRequestDTO){
      try {
          bookingService.modify(bookingRequestDTO);
      }catch (Exception e){
          return ResponseEntity.ok(true);
      }
      return ResponseEntity.ok(false);
  }


    /*삭제
    * 예약취소버튼으로 수행되는 삭제
    * prg패턴으로 삭제이후 메인페이지 보여줌.*//*
    @GetMapping("/booking/delete")
    public String bookingDelete(@RequestParam Long id){
        bookingService.delete(id);
        return "redirect:/hotel";
    }*/

    /*기능 테스트용 삭제
     * 예약취소버튼으로 수행되는 삭제
     * prg패턴으로 삭제이후 메인페이지 보여줌.*/
    @DeleteMapping("/bookingDelete")
    public ResponseEntity<Boolean> bookingDelete(@RequestParam Long id){
        try {
            bookingService.delete(id);
        }catch (Exception e){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }
}
