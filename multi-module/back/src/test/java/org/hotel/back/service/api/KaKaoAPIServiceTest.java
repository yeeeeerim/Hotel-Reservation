package org.hotel.back.service.api;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class KaKaoAPIServiceTest {

    @Autowired
    KaKaoAPIService kaKaoAPIService;

    @Test
    @DisplayName("KAKAOAPI 위도경도 요청")
    void test() throws ParseException {
        System.out.println(kaKaoAPIService.getLocationInfo("미사강변대로 165"));;
    }
    @Test
    @DisplayName("KAKAOAPI 위도경도를 도로명주소로")
    void test2() throws ParseException {
        System.out.println(kaKaoAPIService.getAddressInfo("127.10459896729914","37.40269721785548"));;
    }
}