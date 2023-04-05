package org.hotel.back.service;

import org.assertj.core.api.Assertions;
import org.hotel.back.data.response.FileResponseData;
import org.hotel.back.data.response.RoomDTO;
import org.hotel.back.domain.Room;
import org.hotel.back.repository.RoomRepository;
import org.hotel.back.service.Impl.RoomServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpHeaders;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
//
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@DataJpaTest
@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private RoomCacheService roomCacheService;


    @InjectMocks
    private RoomServiceImpl roomService;


    /**
     * @apiNote 프로퍼티 파일에 등록된 경로에 지정된 장소에 있는 404.jpg 를 리턴하는지 확인
     *
     * */
    @Test
    @DisplayName("File View Test")
    void RoomServiceTest() throws IOException {
        // given
        String fileName = "404.jpg";
        String path = "C:/PROJECT/TAELT/STUDY-GROUP-ACTIVITY/multi-module/back/temp"+ File.separator+fileName;
        Resource resource = new FileSystemResource(path);
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));

        var validation = FileResponseData.builder()
                .headers(headers)
                .resource(resource)
                .build();

        var data =roomService.viewFile(fileName);

        // when

        // then

        Assertions.assertThat(data.getResource().getFilename())
                .isEqualTo(validation.getResource().getFilename());
    }


    /*
    * 테스트방법
    * given() 메소드는 목 객체를 이용해 해당 메소드가 실행될 때, willReturn() 메소드로 지정한 값을 반환하도록 설정하는 것이다.
    * 이떄 실제로 해당 메소드를 실행하지는 않는다. 그리고 InjectMocks로 지정한 Service 클래스의 테스트 메소드에서 해당 메소드를 호출하면
    * 설정한 값이 반환된다. 이렇게 테스트 코드에서 given() 메소드를 사용하여 목 객체에서의 반환 값을 미리 설정해두고, Service 클래스에서 해당 메소드를 호출하여
    * 반환값이 예상한 대로 나오는지 확ㅇ니하는 것이 목 객체를 이용한 테스트 방법이다.
    * */

    @Test
    @DisplayName("Room Id Test")
    void RoomServiceTest2() {
        // given
        given(roomRepository.getRoomWithImage(1L))
                .willReturn(Optional.of(Room.builder()
                        .id(1L)
                        .roomClass("VIP")
                        .build()));
        // when
        RoomDTO roomDTO =  roomService.findByRoomWithImage(1L);

        // then
        Assertions.assertThat(roomDTO.getId()).isEqualTo(1L);
        Assertions.assertThat(roomDTO.getRoomClass()).isEqualTo("VIP");
    }


}