package org.hotel.back.service.Impl;

import org.assertj.core.api.Assertions;
import org.hotel.back.data.response.RoomDTO;
import org.hotel.back.service.RoomCacheService;
import org.hotel.back.service.RoomService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class RoomCacheServiceImplTest {

    @Autowired
    RoomCacheService roomCacheService;
    @Autowired
    RoomService roomService;


    /**
     *  데이터 초기화
     */
    @BeforeEach
    void init(){
            roomCacheService.save(roomService.findByRoomWithImage(1L));
    }

    @Test
    @DisplayName("Redis . 저장된 데이터 조회")
    void RoomCacheServiceImplTest() {
        // given
        var data = roomCacheService.findById(1L);
        // when

        // then
        Assertions.assertThat(data).isNotNull();
        Assertions.assertThat(data.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Service 저장테스트")
    void RoomCacheServiceImplTest2() {
        // given
        var data1 = roomService.findByRoomWithImage(1L);
        System.out.println("레포지토리에서 가져온 거"+data1);
        var data2 = roomService.findByRoomWithImage(1L);
        System.out.println("레디스에서 가져온거"+data2);
        var data3 = roomService.findByRoomWithImage(1L);
        var data4 = roomService.findByRoomWithImage(1L);

        // when

        // then
    }


    @Test
    @DisplayName("Redis . 저장된 데이터 삭제")
    void RoomCacheServiceImplTest3() {
        // given
        RoomDTO dto = roomCacheService.findById(1L);

        // when
        Assertions.assertThat(dto).isNotNull();
        roomCacheService.delete(1L);
        // then

        dto = roomCacheService.findById(1L);
        Assertions.assertThat(dto).isNull();
    }



}