package org.hotel.back.service.api;

import org.hotel.back.data.request.HotelRequestData;
import org.hotel.back.domain.Hotel;
import org.hotel.back.repository.HotelRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class HotelAPIServiceTest {


    @Autowired
    HotelAPIService hotelAPIService;

    @Autowired
    HotelRepository hotelRepository;

    @Test
    @DisplayName("호텔 정보 뽑아보기")
    void test(){

        hotelAPIService.getData(HotelRequestData.builder()
                .key("26f037dc249943e4b75e005d6fc3858d")
                .type("json")
                .build()).getGSST().get(1).getRowList().forEach(row -> {
                    hotelRepository.save(Hotel.builder()
                            .hotelName(row.getENTRPS_NM())
                            .cityName(row.getSIGUN_NM())
                            .tellNumber(row.getTELNO())
                            .latitude(row.getREFINE_WGS84_LAT())
                            .longitude(row.getREFINE_WGS84_LOGT())
                            .build());
                });
        System.out.println();
        //https://openapi.gg.go.kr/GSST?key=26f037dc249943e4b75e005d6fc3858d
//        hotelRepository.save(Hotel.builder()
//                .hotelName(row.getENTRPS_NM())
//                .cityName(row.getSIGUN_NM())
//                .tellNumber(row.getTELNO())
//                .latitude(row.getREFINE_WGS84_LAT())
//                .longitude(row.getREFINE_WGS84_LOGT())
//                .build());
    }
}