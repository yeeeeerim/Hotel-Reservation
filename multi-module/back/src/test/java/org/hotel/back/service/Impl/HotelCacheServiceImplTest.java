package org.hotel.back.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.api.Assertions;
import org.hotel.back.data.response.HotelImageDTO;
import org.hotel.back.data.response.RoomDTO;
import org.hotel.back.domain.HotelImage;
import org.hotel.back.repository.HotelImageRepository;
import org.hotel.back.repository.HotelRepository;
import org.hotel.back.service.HotelImageCacheService;
import org.hotel.back.service.HotelService;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HotelCacheServiceImplTest {
	@Autowired
	HotelImageCacheService hotelImageCacheService;
	@Autowired
	HotelService hotelService;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private HotelImageRepository hotelImageRepository;


//	@BeforeEach
//	void init() throws JsonProcessingException {
//		hotelImageCacheService.save(hotelService.findByHotelImage(118L));
//	}
//
//	@Test
//	@DisplayName("Redis . 저장된 데이터 조회")
//	void RoomCacheServiceImplTest() throws JsonProcessingException {
//		// given
//		var data = hotelImageCacheService.findById(118L);
//		// when
//
//		// then
//		org.assertj.core.api.Assertions.assertThat(data).isNotNull();
//		org.assertj.core.api.Assertions.assertThat(data.getHotelId()).isEqualTo(118L);
//	}

	@Test
	@DisplayName("Service 저장테스트")
	void RoomCacheServiceImplTest2() throws JsonProcessingException {
		hotelImageCacheService.save(hotelService.findByHotelImage(118L));
		// given
		System.out.println("==========="+hotelImageCacheService.findById(118L));
		hotelImageCacheService.delete(118L);
		var data3 = hotelImageCacheService.findById(118L);
		System.out.println("삭제 테 스 트"+data3);
		var data4 = hotelService.findByHotelImage(118L);
		System.out.println("????"+data4);
		// when

		// then
	}

//
//	@Test
//	@DisplayName("Redis . 저장된 데이터 삭제")
//	void RoomCacheServiceImplTest3() {
//		// given
//		RoomDTO dto = roomCacheService.findById(1L);
//
//		// when
//		org.assertj.core.api.Assertions.assertThat(dto).isNotNull();
//		roomCacheService.delete(1L);
//		// then
//
//		dto = roomCacheService.findById(1L);
//		Assertions.assertThat(dto).isNull();
//	}
}