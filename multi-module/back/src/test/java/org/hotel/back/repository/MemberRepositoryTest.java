
package org.hotel.back.repository;

import org.hotel.back.domain.Booking;
import org.hotel.back.domain.Gender;
import org.hotel.back.domain.Member;
import org.hotel.back.domain.MemberRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    BookingRepository bookingRepository;



    @PersistenceContext
    EntityManager entityManager;


    @Test
    @Transactional
    @DisplayName("Member EntityGraph 테스트")
    void test(){
           //    Member member = memberRepository.getMember("owner@naver.com").get();
 //       Member member = memberRepository.findById("owner@naver.com").get();

        List<Member> member = entityManager.createQuery("select m from" +
                " Member m " +
                "left join fetch m.roleSet " +
                "where m.email = :email",Member.class)
                        .setParameter("email","owner@naver.com")
                                .getResultList();

        for (Member member1 : member) {
            System.out.println(member1);
        }


    }


    @Test
    @DisplayName("권한이 OWNER인 Mebmer 생성")
    void test2(){
        memberRepository.save(Member.builder()
                        .email("owner@naver.com")
                        .tellNumber("01012345678")
                        .gender(Gender.MAN)
                        .roleSet(Set.of(MemberRole.ROLE_OWNER))
                        .nickName("닉네임")
                        .password(passwordEncoder.encode("1234"))
                .build());
    }

    @Test
    @WithMockUser(username = "owner", roles = "OWNER")
    @DisplayName("TEST")
    void test33() {
        // given
        Member member = memberRepository.findById("owner@naver.com").get();


        Booking booking = Booking.builder()
                .memberEmail("owner@naver.com")
                .checkIn(LocalDateTime.now().minusDays(7L))
                .checkOut(LocalDateTime.now())
                .roomId(1L)
                .build();

        // when
        bookingRepository.save(booking);

        Booking booking2 = Booking.builder()
                .memberEmail("owner@naver.com")
                .member(member)
                .checkIn(LocalDateTime.now().minusDays(14L))
                .checkOut(LocalDateTime.now().minusDays(7L))
                .roomId(2L)
                .build();

        // when
        bookingRepository.save(booking2);
        // then
    }

    @Test
    @DisplayName("")
    void test44() {
        // given

        // when
        bookingRepository.findByEmail("owner@naver.com").forEach(System.out::println);
        // then
    }

}
