package org.hotel.back.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hotel.back.config.booking.RoomDtoConverter;
import org.hotel.back.config.exception.BookingErrorCode;
import org.hotel.back.config.exception.BookingException;
import org.hotel.back.data.dto.BookingDTO;
import org.hotel.back.data.request.BookingRequestDTO;
import org.hotel.back.data.response.RoomDTO;
import org.hotel.back.domain.Booking;
import org.hotel.back.domain.Room;
import org.hotel.back.repository.BookingRepository;
import org.hotel.back.repository.RoomRepository;
import org.hotel.back.service.BookingService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final RoomRepository roomRepository;

    public List<RoomDTO> findAvailable(LocalDateTime checkIn, LocalDateTime checkOut) {
        System.out.println("ServicecheckIn" + checkIn.getClass().getName());

//        java.sql.Date checkInDate = java.sql.Date.valueOf(checkIn);
//        java.sql.Date checkOutDate = java.sql.Date.valueOf(checkOut);
//        System.out.println("ServicecheckIn" + checkInDate.getClass().getName());

        List<Room> rooms = roomRepository.findAvailableRooms(checkIn, checkOut);

        List<RoomDTO> roomDTOs = new ArrayList<>();

        for (Room room : rooms) {
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setId(room.getId());
            roomDTO.setRoomNumber(room.getRoomNumber());
            roomDTO.setRoomClass(room.getRoomClass());
            roomDTO.setRoomPrice(room.getRoomPrice());
            roomDTO.setRoomLimit(room.getRoomLimit());
            roomDTO.setDescription(room.getDescription());

            roomDTOs.add(roomDTO);
        }


        return roomDTOs;
    }

    public Booking bookingSave(BookingDTO dto) throws BookingException {
        Booking booking = bookingRepository.save(dto.toEntity());
        if (bookingRepository.findById(dto.toEntity().getId()).isEmpty()) {
            throw new BookingException(BookingErrorCode.BOOKING_SAVE_FAIL);
        }
        return booking;
    }

    /**
     * @apiNote id(PK)로 해당 엔티티 단건 조회, 조회 실패할 경우 NOT_FOUND Exception 발생
     */
    public BookingDTO findById(Long id) {
        return BookingDTO.toDTO(bookingRepository.findById(id)
                .orElseThrow(() -> new BookingException(BookingErrorCode.BOOKING_NOT_FOUND)));
    }

    /**
     * SecurityContextHolder 사용해서 로그인 정보 id 값인 memberemail과 파라미터로 전달받은 memberEmail이 같을 경우 updateBooking 실행
     */
    public void updateBooking(LocalDateTime checkIn, LocalDateTime checkOut, String memberEmail, Long id) {
        if (bookingRepository.findById(id).isEmpty()) {
            throw new BookingException(BookingErrorCode.BOOKING_NOT_FOUND);
        } else if ((checkIn.equals(bookingRepository.findById(id).get().getCheckIn()))
                && (checkOut.equals(bookingRepository.findById(id).get().getCheckOut()))) {
            throw new BookingException(BookingErrorCode.BOOKING_NOT_CHANGE);
        } else {

            bookingRepository.updateBooking(id, checkIn, checkOut);
        }
    }
        //TODO: 리스트조회, 삭제
        public List<Booking> getBooking(){
            return bookingRepository.findAll();
        }
        public void delete (Long id){
            bookingRepository.deleteById(id);
            if (!(bookingRepository.findById(id).isEmpty())) {
                throw new BookingException(BookingErrorCode.BOOKING_DELETE_FAIL);
            }
        }
}
