package org.hotel.back.service.security;

import org.hotel.back.data.request.RegisterData;
import org.hotel.back.repository.MemberRepository;
import org.hotel.back.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class MemberDetailsServiceTest {


    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("이름")
    void test(){
        memberService.registerSave(RegisterData.builder().build());
    }


    @Test
    @DisplayName("Member 조회")
    void read(){
        System.out.println(memberRepository.getMember("abc@naver.com"));;
    }

}