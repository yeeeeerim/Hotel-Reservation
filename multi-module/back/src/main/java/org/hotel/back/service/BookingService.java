package org.hotel.back.service;

import org.hotel.back.config.exception.BookingException;
import org.hotel.back.data.dto.BookingDTO;
import org.hotel.back.domain.Booking;
import org.hotel.back.domain.Room;

import java.time.LocalDateTime;
import java.util.List;


public interface BookingService {
    public List<Room> findAvailable(LocalDateTime checkIn, LocalDateTime checkOut);
    public Booking bookingSave(BookingDTO dto) throws BookingException;
    public BookingDTO findById(Long id);
    public void updateBooking(LocalDateTime checkIn, LocalDateTime checkout, Long id);
    public List<Booking> getBooking();
    public void delete(Long id);
}
