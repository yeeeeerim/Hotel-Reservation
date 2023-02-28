package com.back.bookingmodule.service;

import com.back.bookingmodule.data.BookingDTO;
import com.back.bookingmodule.domain.Booking;
import com.back.bookingmodule.domain.Member;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    public Booking bookingSave(BookingDTO dto);
    public Optional<BookingDTO> findById(Long id);
    public void updateBooking(String checkIn, String checkout, Member member, Long id);
    public List<Booking> getBooking();
    public void delete(Long id);

}
