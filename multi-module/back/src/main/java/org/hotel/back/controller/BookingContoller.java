package org.hotel.back.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hotel.back.domain.*;
import org.hotel.back.repository.BookingRepository;
import org.hotel.back.repository.RoomRepository;
import org.hotel.back.service.BookingService;
import org.hotel.back.service.HotelService;
import org.hotel.back.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class BookingContoller {
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    private final BookingService bookingService;
    private final HotelService hotelService;
    private final MemberService memberService;

    /*view 예약된 정보 조회*/
    @GetMapping("/booking/list")
    public String bookingRead(Model model, String checkIn, String checkOut){
        List<Room> availableRoomList = bookingService.findAvailable(checkIn, checkOut);
        model.addAttribute("roomList", availableRoomList);
        return "booking";
    }

    @GetMapping("/booking/date")
    public String selectDate(){
        return "dateSelecte";
    }





//
//    /*view - 예약하기 버튼으로 수행되는 저장
//    * requestDTO의 setter를 통해 바인딩 된 form데이터 저장
//    * prg패턴으로 예약완료된 페이지 보여줌. */
//    @GetMapping("/booking/save")
//    public void bookingSave(@RequestParam Long id){
//        //호텔 객체에 id값으로 조회된 호텔 불러오기
//        Hotel hotel = hotelService.hotelDetail(id);
//        //멤버 객체 불러와야하지만 임시로 멤버 객체 생성.
//        Member member = Member.builder()
//                .email("test1")
//                .password("test1")
//                .tellNumber("011-0202-0202")
//                .gender(Gender.WOMAN)
//                .nickName("mic")
//                .build();
//
//        //request 객체 생성하여 전달.
//        BookingRequestDTO bookingRequestDTO = BookingRequestDTO.builder()
//                .hotel(hotel)
//                .member(member)
//                .createdAt(LocalDateTime.now())
//                .modifiedAt(LocalDateTime.of(2000,01,01,01,01,01))
//                .checkIn(LocalDateTime.of(2000,01,01,01,01,01))
//                .check_out(LocalDateTime.of(2000,01,01,01,01,01))
//                .build();
//
//        //null에러 방지를 위해 localdateTime 기본값 입력.
//
//        bookingService.save(bookingRequestDTO);
//       /* return "redirect:/booing";*/
//    }
//
//
//    /*수정
//    * 조회된 예약정보에서 입실 기능으로 수행되는 수정기능
//    * 입실 및 퇴실시간 자동등록*/
//    @PostMapping("/booking/modify")
//    public String bookingModify(@RequestBody BookingRequestDTO bookingRequestDTO){
//        bookingService.modify(bookingRequestDTO);
//        return "redirect:/booking";
//    }
//
//
//
//    /*삭제
//    * 예약취소버튼으로 수행되는 삭제
//    * prg패턴으로 삭제이후 메인페이지 보여줌.*/
//    @GetMapping("/booking/delete")
//    public String bookingDelete(@RequestParam Long id){
//        bookingService.delete(id);
//        return "redirect:/hotel";
//    }

}
