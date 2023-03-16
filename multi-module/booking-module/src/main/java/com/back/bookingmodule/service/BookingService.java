package com.back.bookingmodule.service;

import com.back.bookingmodule.config.exception.BookingException;
import com.back.bookingmodule.data.BookingDTO;
import com.back.bookingmodule.domain.booking.Booking;

import java.util.List;

public interface BookingService {
    public Booking bookingSave(BookingDTO dto) throws BookingException;
    public BookingDTO findById(Long id);
    public void updateBooking(String checkIn, String checkout, String memberEmail, Long id);
    public List<Booking> getBooking();
    public void delete(Long id);

}
