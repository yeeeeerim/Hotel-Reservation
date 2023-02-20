package org.hotel.back.repository.custom.impl;

import org.hotel.back.repository.RoomRepository;
import org.hotel.back.repository.custom.CustomRoomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;




@SpringBootTest
class CustomRoomRepositoryImplTest {


    @Autowired
    RoomRepository roomRepository;



    @Test
    @DisplayName("호텔 아이디와 작성자 이름으로 찾는 Querydsl")
    void test(){

      //  roomRepository.checkingWriter(96L,"owner@naver.com").ifPresent(System.out::println);
    }
}