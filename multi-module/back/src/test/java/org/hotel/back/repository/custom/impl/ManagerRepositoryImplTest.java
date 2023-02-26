package org.hotel.back.repository.custom.impl;

import org.assertj.core.api.Assertions;
import org.hotel.back.annotation.PerformTimer;
import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.Review;
import org.hotel.back.domain.Room;
import org.hotel.back.repository.HotelRepository;
import org.hotel.back.repository.ReviewRepository;
import org.hotel.back.repository.RoomRepository;
import org.hotel.back.repository.custom.ManagerRepository;
import org.hotel.back.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestPropertySource(properties = "spring.jpa.properties.hibernate.default_batch_fetch_size=10") // 옵션 적용
class ManagerRepositoryImplTest {

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ReviewRepository reviewRepository;
    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberService memberService;

    @Test
    @Transactional
    @WithMockUser(username = "jake", roles = "USER")
    @DisplayName("이메일을 통해 호텔정보가져오기 테스트")
    void hotelInfoTest(){
        /*
        *  room 데이터 1000개
        *  review 데이터 1000개
        * 테스트 결과 성능이 미쳐날뛰어 실행에러남... join은 한 개만
        *
        *  리뷰가 1000개 기준
        *  리뷰만 묶으면 0.35
        *  룸이 10개 일 경우 묶으면 0.5
        *  룸이 20개 일 경우 묶으면 0.69  10개당 0,19초 추가
        *
        *
        *  쿼리를 따로 일으킬 경우 0.4 + 0.08 == 0.48
        *
        *
        *  BatchSize() 사용할 경우
        *  0.65
        *
        * 결과를 통해 REview가 더 많을 것을 판단되어 리뷰를 Fetch join을 ㅗ묵고 룸은 따로 쿼리를 한번 더 일으킬 생각
        * */

        var hotel = managerRepository.getHotelInfo("owner@naver.com");
        System.out.println(hotel);

        System.out.println(hotel.getReviews().get(0).getReviewContent());
        managerRepository.findByRoom(hotel.getId()).forEach(System.out::println);   //이게 제일 최선인듯
    }능

    @Test
    @WithMockUser(username = "jake", roles = "USER")
    void reviewSave(){
        Hotel hotel = hotelRepository.findById(2L).get();
        IntStream.rangeClosed(1, 1000).forEach(value -> {
                reviewRepository.save(Review.builder()
                                .hotel(hotel)
                                .reviewContent("테스트 데이터,,,,."+value)
                        .build());

        });
    }


    @Test
    @Modifying
    @Transactional
    void testData(){
            LongStream.rangeClosed(40,490).forEach(value -> {
                roomRepository.deleteById(value);
            });
    }

    @Test
    void insertData(){
            IntStream.rangeClosed(11,20).forEach(value -> {
                roomRepository.save(Room.builder()
                                .hotelId(2L)
                                .description("테스터.."+value)
                        .build());
            });
    }
}