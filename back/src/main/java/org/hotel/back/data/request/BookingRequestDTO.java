package org.hotel.back.data.request;

import lombok.*;
import org.hotel.back.domain.Booking;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
public class BookingRequestDTO {

    private Long bookingId;
    private Long hotelId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long memberId;
    private LocalDateTime checkIn;
    private LocalDateTime check_out;

    public static Booking toEntity(BookingRequestDTO bookingRequestDTO){
        return Booking.builder()
                .bookingId(bookingRequestDTO.bookingId)
                .hotelId(bookingRequestDTO.hotelId)
                .createdAt(bookingRequestDTO.createdAt)
                .modifiedAt(bookingRequestDTO.modifiedAt)
                .memberId(bookingRequestDTO.memberId)
                .checkIn(bookingRequestDTO.checkIn)
                .check_out(bookingRequestDTO.check_out)
                .build();
    }
}
