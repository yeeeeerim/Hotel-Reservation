package org.hotel.back.controller;

import org.assertj.core.api.Assertions;
import org.hotel.back.config.JpaAuditingConfig;
import org.hotel.back.data.response.RoomDTO;
import org.hotel.back.service.RoomService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @WebMvcTest 해당 컨트롤러와 관련된 빈들만 로딩하기 때문에, 해당 컨트롤러가 의존하는 빈들을 목 객체로
 * 대체하여 사용하는 것이 일반적, 따라서 @MockBean으로 선언한 객체들은 가짜(Mock) 객체이며 실제 빈을 대체하여 사용하는 것이다.
 *
 * */
@WebMvcTest(RoomController.class)
@AutoConfigureMockMvc(addFilters = false)   //필터비활성화
class RoomControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;


    @MockBean
    private JpaAuditingConfig jpaAuditingConfig;



    /**
     * @WithMockUser는 Security 테스트에서 애노테이션으로 특정 사용자 정보를 모킹하여 해당 사용자로 인즈된 것처럼 테스트할 수 있도록함
     * @apiNote /room/list  1번 소풍호텔에 한 개에 room이 달려있음
     * @success
     * */
    @Test
    @WithMockUser(username = "owner",password = "password", roles = {"OWNER"})
    @DisplayName("ROOM GET을 통해 리스트 조회")
    void test() throws Exception {
      MvcResult result =  mockMvc.perform(MockMvcRequestBuilders
                        .get("/room/list")
                        .param("id","1")
                        .param("hotelNa","소풍호텔")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                 .andReturn(); //andReturn을 통해 ResultAction이 아닌 MvcResult를 반환한다.

        ModelAndView modelAndView  = result.getModelAndView();
        ModelMap modelMap = modelAndView.getModelMap();

        Assertions.assertThat(modelMap.get("hotelNa"))
                .isEqualTo("소풍호텔");
    }

    /**
     * @apiNote /room/save  해당 주소에  RoomDTO데이터를 from 데이터로 보내면 해당 컨트롤러는 리다이렉션시킨다.
     * @success
     * */
    @Test
    @WithMockUser(username = "owner@naver.com",password = "1234",authorities={ "OWNER"})
    @DisplayName("룸 POST요청 날리기")
    void RoomControllerTest() throws Exception {
        // given
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("roomNumber","404");
        formData.add("roomClass","VIP");
        formData.add("roomPrice","120,000");
        formData.add("description","넓은 방입니다");

        RoomDTO roomDTO  = RoomDTO.builder()
                .roomNumber("404")
                .roomClass("VIP")
                .roomPrice("120,000")
                .description("넓은 방입니다.")
                .hotelId("1")
                .build();

        // when
        mockMvc.perform(MockMvcRequestBuilders
                .post("/room/save")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("roomDTO",roomDTO))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/room/list&id=1"));

       // then
        Mockito.verify(roomService).save(roomDTO);
    }


    /**
     * @apiNote /room/detail  해당 주소에  hotelId 그리고 hotelNa 값을 차례대로 넣으면 호텔
     * */
    @Test
    @DisplayName("/room/detail GET Method Test")
    void test2() throws Exception {
        // given
        mockMvc.perform(MockMvcRequestBuilders.get("/room/detail")
                .param("id","1")
                .param("hotelNa","소풍호텔")
                .param("hotelId","1")
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }



}