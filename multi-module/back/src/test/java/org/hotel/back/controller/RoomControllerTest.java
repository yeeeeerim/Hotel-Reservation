package org.hotel.back.controller;

import org.assertj.core.api.Assertions;
import org.hotel.back.config.JpaAuditingConfig;
import org.hotel.back.repository.RoomRepository;
import org.hotel.back.service.MemberService;
import org.hotel.back.service.RoomService;
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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @WebMvcTest 해당 컨트롤러와 관련된 빈들만 로딩하기 때문에, 해당 컨트롤러가 의존하는 빈들을 목 객체로
 * 대체하여 사용하는 것이 일반적, 따라서 @MockBean으로 선언한 객체들은 가짜(Mock) 객체이며 실제 빈을 대체하여 사용하는 것이다.
 *
 * */
@WebMvcTest(RoomController.class)
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
    @WithMockUser(username = "owner", roles = {"ROLE_OWNER"})
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
     * @WithMockUser는 Security 테스트에서 애노테이션으로 특정 사용자 정보를 모킹하여 해당 사용자로 인즈된 것처럼 테스트할 수 있도록함
     * @apiNote /room/list  1번 소풍호텔에 한 개에 room이 달려있음
     * @success
     * */

    @Test
    @WithMockUser(username = "owner", roles = {"ROLE_OWNER"})
    @DisplayName("1번 룸을 조회하기")
    void RoomControllerTest() {
        // given

        // when

        // then
    }


}