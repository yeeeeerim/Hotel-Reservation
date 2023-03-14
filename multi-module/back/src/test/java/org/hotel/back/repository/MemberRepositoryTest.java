//package org.hotel.back.repository;
//
//import org.hotel.back.domain.Gender;
//import org.hotel.back.domain.Member;
//import org.hotel.back.domain.MemberRole;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@SpringBootTest
//class MemberRepositoryTest {
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//
//    @Test
//    @DisplayName("Member EntityGraph 테스트")
//    void test(){
//        Member member = memberRepository.getMember("test").get();
//
//        Assertions.assertNotNull(member);
//    }
//
//
//    @Test
//    @DisplayName("권한이 OWNER인 Mebmer 생성")
//    void test2(){
//        memberRepository.save(Member.builder()
//                        .email("owner@naver.com")
//                        .tellNumber("01012345678")
//                        .gender(Gender.MAN)
//                        .roleSet(Set.of(MemberRole.ROLE_OWNER))
//                        .nickName("업주")
//                        .password(passwordEncoder.encode("1234"))
//                .build());
//    }
//
//}