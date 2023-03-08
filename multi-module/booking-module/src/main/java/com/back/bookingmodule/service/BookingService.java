package com.back.bookingmodule.service;

import com.back.bookingmodule.data.BookingDTO;
import com.back.bookingmodule.domain.Booking;
import com.back.bookingmodule.domain.Member;

import java.util.List;

public interface BookingService {
    public Booking bookingSave(BookingDTO dto);
    public BookingDTO findById(Long id);
    public void updateBooking(String checkIn, String checkout, String memberEmail, Long id);
    public List<Booking> getBooking();
    public void delete(Long id);

}
