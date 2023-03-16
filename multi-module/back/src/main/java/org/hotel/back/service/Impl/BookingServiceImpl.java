package org.hotel.back.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hotel.back.config.exception.BookingErrorCode;
import org.hotel.back.config.exception.BookingException;
import org.hotel.back.data.dto.BookingDTO;
import org.hotel.back.domain.Booking;
import org.hotel.back.domain.Room;
import org.hotel.back.repository.BookingRepository;
import org.hotel.back.service.BookingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;


    public List<Room> findAvailable(LocalDateTime checkIn, LocalDateTime checkOut) {
        return bookingRepository.getNotReservation(checkIn, checkOut);
    }

    public Booking bookingSave(BookingDTO dto){
        Booking booking = bookingRepository.save(dto.toEntity());
        if (bookingRepository.findById(dto.toEntity().getId()).isEmpty()){
            throw new BookingException(BookingErrorCode.BOOKING_SAVE_FAIL);
        }
        return booking;
    }

    /**
     * @apiNote id(PK)로 해당 엔티티 단건 조회, 조회 실패할 경우 NOT_FOUND Exception 발생
     */
    public BookingDTO findById(Long id){
        return BookingDTO.toDTO(bookingRepository.findById(id)
                .orElseThrow(() -> new BookingException(BookingErrorCode.BOOKING_NOT_FOUND)));
    }

    /**
     *
     * */
    public void updateBooking(LocalDateTime checkIn, LocalDateTime checkout, Long id){
        if (bookingRepository.findById(id).isEmpty()){
            throw new BookingException(BookingErrorCode.BOOKING_NOT_FOUND);
        } else if ((checkIn.equals(bookingRepository.findById(id).get().getCheckIn()))
                && (checkout.equals(bookingRepository.findById(id).get().getCheckOut()))) {
            throw new BookingException(BookingErrorCode.BOOKING_NOT_CHANGE);
        }else {
            bookingRepository.updateBooking(checkIn, checkout, id);
        }
    }


    //TODO: 리스트조회, 삭제

    public List<Booking> getBooking(){
        return bookingRepository.findAll();
    }
    public void delete(Long id){
        bookingRepository.deleteById(id);
        if (!(bookingRepository.findById(id).isEmpty())){
            throw new BookingException(BookingErrorCode.BOOKING_DELETE_FAIL);
        }
    }
}