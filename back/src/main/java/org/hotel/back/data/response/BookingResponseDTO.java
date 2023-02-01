package org.hotel.back.data.response;

import lombok.*;
import org.hotel.back.domain.Booking;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
public class BookingResponseDTO {
    private Long bookingId;
    private Long hotelId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long memberId;
    private LocalDateTime checkIn;
    private LocalDateTime check_out;

    public Booking toEntity(BookingResponseDTO bookingResponseDTO){
        return Booking.builder()
                .bookingId(bookingResponseDTO.bookingId)
                .hotelId(bookingResponseDTO.hotelId)
                .createdAt(bookingResponseDTO.createdAt)
                .modifiedAt(bookingResponseDTO.modifiedAt)
                .memberId(bookingResponseDTO.memberId)
                .checkIn(bookingResponseDTO.checkIn)
                .check_out(bookingResponseDTO.check_out)
                .build();
    }

}
