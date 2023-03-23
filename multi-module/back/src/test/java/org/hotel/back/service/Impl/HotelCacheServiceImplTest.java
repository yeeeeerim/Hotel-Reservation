package org.hotel.back.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.hotel.back.data.response.HotelImageDTO;
import org.hotel.back.domain.HotelImage;
import org.hotel.back.repository.HotelImageRepository;
import org.hotel.back.repository.HotelRepository;
import org.hotel.back.service.HotelImageCacheService;
import org.hotel.back.service.HotelService;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
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


	@BeforeEach
	}

	@Test
	@DisplayName("Redis . 저장된 데이터 조회")
	void RoomCacheServiceImplTest() throws ParseException, JsonProcessingException {
		System.out.println(hotelService.findByHotelImage(108L));
		System.out.println("---------"+hotelImageCacheService.findById(108L));

	}


}