package org.hotel.back.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hotel.back.data.dto.MemberDTO;
import org.hotel.back.data.response.HotelAndReviewDTO;
import org.hotel.back.data.response.HotelResponseDTO;
import org.hotel.back.data.response.RoomDTO;
import org.hotel.back.service.HotelService;
import org.hotel.back.service.MemberService;
import org.hotel.back.service.RoomService;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class HotelRestController {




    private final RoomService roomService;



//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/api/hotel")
//    public ResponseEntity<HotelAndReviewDTO> getRestHotelDATA(
//            @AuthenticationPrincipal MemberDTO memberDTO
//            ){
//        if(memberDTO != null){
//            try {
//                return ResponseEntity.ok(memberService.get(memberDTO.getEmail()));
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//        }else{
//            return ResponseEntity.badRequest().body(null);
//        }
//    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/room")
    public ResponseEntity<RoomDTO> getRestRoomDATA(
            @RequestParam String id,
            @AuthenticationPrincipal MemberDTO memberDTO
    ){

        log.info("들어온 아이디 값 : {}",id);
        if(memberDTO != null){
                return ResponseEntity.ok(
                        roomService.findByRoomWithImage(Long.parseLong(id))
                );
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }


}
