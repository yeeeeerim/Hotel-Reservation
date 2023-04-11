package org.hotel.back.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hotel.back.data.request.HotelRequestDTO;
import org.hotel.back.repository.HotelRepository;
import org.hotel.back.service.HotelImageCacheService;
import org.hotel.back.service.HotelService;
import org.hotel.back.service.api.KaKaoAPIService;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

@WebMvcTest(HotelController.class)
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private HotelService hotelService;
    @MockBean
    private HotelController hotelController;
    @MockBean
    private KaKaoAPIService kaKaoAPIService;
    @MockBean
    private HotelImageCacheService hotelImageCacheService;
    @MockBean
    private HotelRepository hotelRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;


    /**
     * Hotel Post 저장(전체 기능 성공)
     * 테스트 과정) 1. 먼저 HotelRequestDTO 객체를 생성, 이를 MultiValueMap으로 변환.
     *          (이 MultiValueMap은 HTTP 요청에서 파라미터를 전달하는 데 사용)
     *          2. MockMvc를 사용하여 /hotel/save 엔드포인트에 POST 요청
     *          given을 사용하여 hotelService의 write 메서드를 호출 및 실패 메시지를 제공
     *           3. mockMvc.perform을 사용하여 /hotel/save 엔드포인트에 POST 요청
     *           실제 HotelController의 post 메서드를 호출 (컨트롤러 메소드 호출확인)
     */
    @Test
    @DisplayName("Hotel Post를 통해 저장")
	@WithMockUser(username = "owner", roles = "OWNER")
    void test222() throws Exception {
        HotelRequestDTO hotelRequestDTO = HotelRequestDTO.builder()
                .hotelName("hotelName")
                .cityName("cityName")
                .tellNumber("tell")
                .address("경기도 시흥시 중심상가로 285")
                .build();

        // HotelRequestDTO 객체를 MultiValueMap<String, String>으로 변환
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        Map<String, String> hotelRequestMap = new HashMap<>();
        hotelRequestMap.put("hotelName", hotelRequestDTO.getHotelName());
        hotelRequestMap.put("cityName", hotelRequestDTO.getCityName());
        hotelRequestMap.put("tellNumber", hotelRequestDTO.getTellNumber());
        hotelRequestMap.put("address", hotelRequestDTO.getAddress());
        for (Map.Entry<String, String> entry : hotelRequestMap.entrySet()) {
            params.add(entry.getKey(), entry.getValue());
        }

        // MockMvc를 사용하여 테스트
        MvcResult result=mockMvc.perform(MockMvcRequestBuilders
                .post("/hotel/save")
                .params(params)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }






}

