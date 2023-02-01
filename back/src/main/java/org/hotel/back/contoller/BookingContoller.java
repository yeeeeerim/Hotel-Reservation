package org.hotel.back.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hotel.back.data.request.BookingRequestDTO;
import org.hotel.back.repository.BookingRepository;
import org.hotel.back.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@Slf4j
public class BookingContoller {

    private final BookingService bookingService;

    /*예약된 정보 조회*/
    @GetMapping("/booking")
    public String bookingRead(Model model, @RequestParam Long id){
        model.addAttribute("booking",bookingService.read(id));
        return "booking";
    }

    /*예약하기 버튼으로 수행되는 저장
    * requestDTO의 setter를 통해 바인딩 된 form데이터 저장
    * prg패턴으로 예약완료된 페이지 보여줌. */
    @PostMapping("/hotel/detail/save")
    public String bookingSave(BookingRequestDTO bookingRequestDTO){
        bookingService.save(bookingRequestDTO);
        return "redirect:/booking";
    }

    /*수정
    * 조회된 예약정보에서 입실 기능으로 수행되는 수정기능
    * 입실 및 퇴실시간 자동등록*/
    @PostMapping("/booking/modify")
    public String bookingModify(BookingRequestDTO bookingRequestDTO){
        bookingService.modify(bookingRequestDTO);
        return "redirect:/booking";
    }


    /*삭제
    * 예약취소버튼으로 수행되는 삭제
    * prg패턴으로 삭제이후 메인페이지 보여줌.*/
    @GetMapping("/booking/delete")
    public String bookingDelete(@RequestParam Long id){
        bookingService.delete(id);
        return "redirect:/hotel";
    }
}
