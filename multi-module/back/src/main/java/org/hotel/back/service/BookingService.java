package org.hotel.back.service;

import org.hotel.back.config.exception.BookingException;
import org.hotel.back.data.dto.BookingDTO;
import org.hotel.back.data.request.BookingRequestDTO;
import org.hotel.back.data.response.BookingResponseDTO;
import org.hotel.back.data.response.RoomDTO;
import org.hotel.back.domain.Booking;
import org.hotel.back.domain.Room;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public interface BookingService {
    public List<RoomDTO> findAvailable(LocalDateTime checkIn, LocalDateTime checkOut, Long hotelId);

    public Booking bookingSave(BookingDTO dto) throws BookingException;
    public BookingDTO findById(Long id);
    public void updateBooking(LocalDateTime checkIn, LocalDateTime checkout, String memberEmail, Long id);
    public List<BookingResponseDTO> bookingList();
    public void delete(Long id);

}
