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

    @Test
    @Transactional
    @PerformTimer
    @DisplayName("이메일을 통해 호텔정보가져오기 테스트")
    void hotelInfoTest(){
        /*
        *  room 데이터 1000개
        *  review 데이터 1000개
        * 테스트 결과 성능이 미쳐날뛰어 실행에러남... join은 한 개만
        *
        *
        * */

        var hotel = managerRepository.getHotelInfo("owner@naver.com");
            System.out.println(hotel);
         //   hotel.getRoomSet().stream().limit(4L).forEach(room -> System.out.println(room.getDescription()));

            System.out.println(hotel.getReviews().get(0).getReviewContent());
    }

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
            LongStream.rangeClosed(30,500).forEach(value -> {
                roomRepository.deleteById(value);
            });
    }
}