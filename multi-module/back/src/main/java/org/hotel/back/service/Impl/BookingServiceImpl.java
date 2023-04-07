
package org.hotel.back.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hotel.back.config.exception.BookingErrorCode;
import org.hotel.back.config.exception.BookingException;
import org.hotel.back.data.dto.BookingDTO;
import org.hotel.back.data.dto.MemberDTO;
import org.hotel.back.data.request.BookingRequestDTO;
import org.hotel.back.data.response.BookingResponseDTO;
import org.hotel.back.data.response.RoomDTO;
import org.hotel.back.domain.Booking;
import org.hotel.back.domain.Member;
import org.hotel.back.domain.Room;
import org.hotel.back.repository.BookingRepository;
import org.hotel.back.repository.RoomRepository;
import org.hotel.back.service.BookingService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final RoomRepository roomRepository;

    public List<RoomDTO> findAvailable(LocalDateTime checkIn, LocalDateTime checkOut, Long hotelId) {
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
        return booking;
    }

    /**
     * @apiNote id(PK)로 해당 엔티티 단건 조회, 조회 실패할 경우 NOT_FOUND Exception 발생
     */
    public BookingResponseDTO findById(Long id) {
        BookingDTO bookingDTO = BookingDTO.toDTO(bookingRepository.findById(id)
                .orElseThrow(() -> new BookingException(BookingErrorCode.BOOKING_NOT_FOUND)));
        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
        bookingResponseDTO.setId(id);
        bookingResponseDTO.setRoomId(bookingDTO.getRoomId());
        bookingResponseDTO.setCheckIn(bookingDTO.getCheckIn().toString());
        bookingResponseDTO.setCheckOut(bookingDTO.getCheckOut().toString());
        bookingResponseDTO.setMemberEmail(bookingDTO.getMemberEmail());
        return bookingResponseDTO;
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

            bookingRepository.updateBooking(checkIn, checkOut,id);
        }
    }
    //TODO: 리스트조회, 삭제
    public List<BookingResponseDTO> bookingList(){
        List<Booking> bookingList = bookingRepository.findAll();
        List<BookingResponseDTO> bookingResponseDTOList = new ArrayList<>();
        for (Booking booking : bookingList){
            BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
            bookingResponseDTO.setId(booking.getId());
            bookingResponseDTO.setRoomId(booking.getRoomId());
            bookingResponseDTO.setCheckIn(booking.getCheckIn().toString());
            bookingResponseDTO.setCheckOut(booking.getCheckOut().toString());
            bookingResponseDTO.setMemberEmail(booking.getMemberEmail());
            bookingResponseDTOList.add(bookingResponseDTO);
        }
        return bookingResponseDTOList;
    }

    @Transactional
    public void delete (Long id){
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            Booking b = booking.get();
            b.setDeleted(true);
            System.out.println(b.getDeleted());
        }
    }
}