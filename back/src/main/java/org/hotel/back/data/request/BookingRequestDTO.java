package org.hotel.back.data.request;

import lombok.*;
import org.hotel.back.domain.Booking;
import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.Member;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
public class BookingRequestDTO {

    private Long bookingId;
    private Hotel hotel;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Member member;
    private LocalDateTime checkIn;
    private LocalDateTime check_out;

    public static Booking toEntity(BookingRequestDTO bookingRequestDTO){
        return Booking.builder()
                .bookingId(bookingRequestDTO.bookingId)
                .hotel(bookingRequestDTO.hotel)
                .createdAt(bookingRequestDTO.createdAt)
                .modifiedAt(bookingRequestDTO.modifiedAt)
                .member(bookingRequestDTO.member)
                .checkIn(bookingRequestDTO.checkIn)
                .check_out(bookingRequestDTO.check_out)
                .build();
    }
}
