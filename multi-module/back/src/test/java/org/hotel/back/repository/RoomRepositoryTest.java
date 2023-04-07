package org.hotel.back.repository;

import org.assertj.core.api.Assertions;
import org.hotel.back.data.response.RoomDTO;
import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.Room;
import org.hotel.back.domain.RoomImage;
import org.hotel.back.service.RoomService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.jpa.repository.Query;

import org.springframework.security.test.context.support.WithMockUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RoomRepositoryTest {

    @Autowired
    RoomRepository roomRepository;


    @PersistenceContext
    EntityManager em;

    @Test
    void test222(){
        em.createQuery( "SELECT DISTINCT r FROM Room r LEFT JOIN r.booking b WHERE r.hotelId = :hotelId AND (b.checkOut <= :checkIn OR b.checkIn >= :checkOut)")
                .setParameter("hotelId", 178L)
                .setParameter("checkIn", LocalDateTime.now().minusDays(1))
                .setParameter("checkOut", LocalDateTime.now())
                .getResultList().forEach(System.out::println);
    }
//체크인 4월 7일
    //체크아웃 4월 8일

    //체크아웃 4월 7일 이전
    //체크인 4월 8일 이후

    @Autowired
    TestEntityManager entityManager;

    @BeforeEach
    public void setUp(){

        Room room = Room.builder()
                .hotelId(1L)
                .roomLimit("4")
                .roomPrice("120,000")
                .roomNumber("404")
                .build();

        room.addImage("404.jpg");
        entityManager.persist(room);
    }
//    @Query("SELECT r FROM Room r WHERE r.id = :id")
//    public Optional<Room> getRoomWithImage(@Param("id") Long id);
//
//
//    @Query("SELECT DISTINCT r FROM Room  r LEFT JOIN FETCH r.roomImage WHERE r.hotel.id = :id")
//    public List<Room> roomListWithImage(@Param("id") Long id);
//


    /**
     *
     * 실제 쿼리와 실제 데이터 들어옴 주의가 필요하다
     * */
    @Test
    @DisplayName("getRoomWithImage 테스트")
    void test1() {
        // given
        Room room = roomRepository.getRoomWithImage(1L).get();
        Set<RoomImage> roomImage = room.getRoomImage();
        // when
        String fileName = roomImage.stream().findFirst().get().getName();
        // then
        Assertions.assertThat(fileName).isEqualTo("b4197ce5-f4d4-4041-9b2a-f3ab8514a14c_testImge2.jpg");
        Assertions.assertThat(room.getRoomLimit()).isEqualTo("3");

    }



}