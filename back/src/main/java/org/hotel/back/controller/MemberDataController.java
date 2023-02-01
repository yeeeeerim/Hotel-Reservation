package org.hotel.back.controller;


import lombok.RequiredArgsConstructor;
import org.hotel.back.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberDataController {

    private final MemberService memberService;


    /**
     * @param email을 통해 해당 이메일이 존재하는지를 확인한다.
     * @apiNote 만약 있으면 false를 없다면 true를 반환함 true를 반환한다면 그 이메일을 사용해도됨
     * */

    @GetMapping("/member")
    public ResponseEntity<Boolean> emailCheck(@RequestParam String email){
        return ResponseEntity.ok(!memberService.checkEmail(email));
    }

}
