//package org.hotel.back.repository;
//
//import org.hotel.back.data.response.RoomDTO;
//import org.hotel.back.domain.Hotel;
//import org.hotel.back.domain.Room;
//import org.hotel.back.service.RoomService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.test.context.support.WithMockUser;
//
//import java.util.List;
//import java.util.stream.IntStream;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@SpringBootTest
//class RoomRepositoryTest {
//
//    @Autowired
//    HotelRepository hotelRepository;
//
//    @Autowired
//    RoomRepository roomRepository;
//
//    @Autowired
//    RoomService roomService;
//
//
//    @Test
//    @DisplayName("룸 리스트를 보여줄때 이미지도 필요하기 때문에 시도한 테스트")
//    void test(){
//        Hotel hotel = hotelRepository.save(Hotel.builder()
//                        .cityName("테스트")
//                        .tellNumber("xptmxm")
//                        .hotelName("테스트호텔")
//                .build());
//        Room room = Room.builder()
//
//                .hotelId(hotel.getId())
//                .roomClass("테스트클래스")
//                .roomLimit("3")
//                .build();
//        room.addImage("테스트이미지3.jpg");
//
//        Room room2 = Room.builder()
//                .hotelId(hotel.getId())
//                .roomClass("테스트클래스")
//                .roomLimit("4")
//                .build();
//        room.addImage("테스트이미지4.jpg");
//
//
//        roomRepository.save(room);
//        roomRepository.save(room2);
//        List<Room> test = roomRepository.roomListWithImage(hotel.getId());
//            test.forEach(room1 -> {
//                room1.getRoomImage().forEach(System.out::println);
//            });
//    }
//
//    @Test
//    @DisplayName("JPQL로 룸 리스트 불러오기 테스트")
//    void test3(){
//       // select * from room left join room_image ri on room.id = ri.room_id where room.hotel_id = 1;
//        //해당 네이티브 쿼리와 동일함
//        roomService.findAllWithImage(1L).forEach(System.out::println);
//    }
//
//    @Test
//    @DisplayName("사용자 검증 테스트")
//    void testQuery(){
//         roomRepository.getRoomWithImage(13L);
//
//         roomRepository.getRoomWithImageOne(13L);
//
//        System.out.println("3");
//        // roomRepository.getRoomWithImageTwo(13L);
//
//
//    }
//
//    @Test
//    @WithMockUser(username = "owner", roles = "OWNER")
//    @DisplayName("")
//    void RoomRepositoryTest() {
//        // given
//        String str = "b164a891-6a1f-4d6f-9e72-3a1a2e5553dc_제목을-입력해주세요_-001 (6).png";
//        // when
//        IntStream.rangeClosed(1,5).forEach(value -> {
//            roomService.save(RoomDTO.builder()
//                            .roomNumber(Integer.toString(value))
//                            .roomPrice(Integer.toString(value+100))
//                            .roomClass(Integer.toString(value+10))
//                            .description("정말 좋은 방..."+value)
//                            .fileNames(List.of(str))
//                            .hotelId("1")
//                    .build());
//        });
//        // then
//
//    }
//}