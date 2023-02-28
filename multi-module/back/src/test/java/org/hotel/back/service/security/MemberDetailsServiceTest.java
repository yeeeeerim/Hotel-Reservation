package org.hotel.back.service.security;

import org.assertj.core.api.Assertions;
import org.hotel.back.data.request.RegisterData;
import org.hotel.back.repository.MemberRepository;
import org.hotel.back.service.MemberService;
import org.json.simple.parser.ParseException;
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

    @Test
    @DisplayName("TEST to view the Hotel/Review data created by the author by email")
    void MemberDetailsServiceTest() throws ParseException {
        // given
        String email = "owner@naver.com";
        // when
        var data = memberService.getHotelAndReviewWithRoom(email);

        // then
        Assertions.assertThat(data).isNotNull();
    }

}