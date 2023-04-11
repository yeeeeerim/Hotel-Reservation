package org.hotel.back.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BookingRepositoryTest {


    @Autowired
    BookingRepository bookingRepository;

    @PersistenceContext
    EntityManager entityManager;


    @Test
    @Transactional
    void test(){
        bookingRepository.findByEmail("owner@naver.com").forEach(System.out::println);

      //  bookingRepository.findAll().forEach(System.out::println);
    }

    @Test
    void test2(){
        entityManager.createQuery("select b from Booking b join fetch b.room where b.memberEmail = :email")
                .setParameter("email","owner@naver.com")
                .getResultList().forEach(System.out::println);
    }
}