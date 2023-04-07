package com.back.bookingmodule.prog;

import com.back.bookingmodule.domain.Member;
import com.back.bookingmodule.domain.booking.Booking;
import com.back.bookingmodule.repository.BookingRepository;
import com.back.bookingmodule.service.BatchService;
import com.back.bookingmodule.service.impl.BatchServiceImpl;
import org.hibernate.engine.jdbc.batch.spi.Batch;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BookingConfigurationTest {


    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    BatchServiceImpl batchService;

<<<<<<< HEAD
    @BeforeEach
=======
   // @BeforeEach
>>>>>>> 527d5079c141d5a2fb705066adbd502aa53ed9e1
    void init(){
        bookingRepository.findAll().forEach(System.out::println);
        bookingRepository.deleteAll();

        IntStream.rangeClosed(1,5).forEach(day -> {
            bookingRepository.save(Booking.builder()
                    .member(Member.builder().email("owner@naver.com").build())
                    .checkIn(LocalDateTime.now().minusDays(day+7))
                    .checkOut(LocalDateTime.now().minusDays(day))
                    .build());
        });
    }

<<<<<<< HEAD
    @AfterEach
    void clear(){
//        bookingRepository.findAll().forEach(booking -> {
//                if(booking.isDeleted()) bookingRepository.deleteById(booking.getId());
//        });
    }
=======

>>>>>>> 527d5079c141d5a2fb705066adbd502aa53ed9e1

    @Test
    @DisplayName("Check-in data query verification")
    void BookingConfigurationTest() throws Exception {
        // given
        System.out.println("테스트코드 쿼리=============");
        List<Booking> data = entityManager
                .createQuery("select b from Booking b where b.checkOut <  :date and b.deleted = false")
                .setParameter("date",LocalDateTime.now())
                .getResultList();

        // when

        batchService.job();
        // then
        assertThat(data.size()).isEqualTo(5);

        data.forEach(entity -> {
            assertThat(entity.getCheckOut())
                    .matches(localDateTime ->
                            localDateTime.isBefore(LocalDateTime.now()));
        });
    }

<<<<<<< HEAD
=======
    @Test
    @DisplayName("")
    void test() throws Exception {
        // given
        batchService.hotelJob();
        // when

        // then
    }

>>>>>>> 527d5079c141d5a2fb705066adbd502aa53ed9e1
}