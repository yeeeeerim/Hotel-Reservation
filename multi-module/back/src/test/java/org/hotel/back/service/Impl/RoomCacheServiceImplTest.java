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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
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


    /**
     * PK 1번 값을 불러와서 해당 데이터를 Redis에 저장시키면서 가져온다.
     *  그렇게 불러온 데이터를 업데이트를 시키고
     *  Redis에 데이터가 잘 저장되었는지 확인한다.
     *  그러면 Redis에서 가져온 데이터와 수정한 뒤 저장된 레디스 데이터가 같은지 체크한다.
     * */
    @Test
    @WithMockUser(username = "owner", roles = "OWNER")
    @DisplayName("Service 저장테스트")
    void RoomCacheServiceImplTest2() {
        // given
        RoomDTO dbData = roomService.findByRoomWithImage(1L);
        String dbDesc = dbData.getDescription();
        String desc = "좋은 방일까? 그런걸까? 레디스 체크데이";
        // when
        dbData.setDescription(desc);
        boolean result = roomService.modifyRoom(dbData);

        RoomDTO dto = roomCacheService.findById(1L);
        RoomDTO dto2 = roomService.findByRoomWithImage(1L);

        // then
        Assertions.assertThat(dbDesc).isNotEqualTo(desc);

        Assertions.assertThat(dbData).isNotNull();
        Assertions.assertThat(result).isTrue();
        Assertions.assertThat(dto).isNull();
        Assertions.assertThat(dto2.getDescription()).isEqualTo(desc);
        Assertions.assertThat(dto2).isNotNull();
        Assertions.assertThat(dto2.getId()).isEqualTo(dbData.getId());

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