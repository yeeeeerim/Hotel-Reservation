package org.hotel.back.data.response;

import lombok.*;
import org.hotel.back.domain.Booking;

import java.util.Optional;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingResponseDTO {
    private Long id;
    private Long roomId;
    private String checkIn;
    private String checkOut;
    private String memberEmail;

    public BookingResponseDTO toResponseDTO(Optional<Booking> booking){
        String checkIn = booking.get().getCheckIn().toString();
        String checkOut = booking.get().getCheckOut().toString();
        return BookingResponseDTO.builder()
                .id(booking.get().getId())
                .roomId(booking.get().getRoomId())
                .checkIn(checkIn)
                .checkOut(checkOut)
                .memberEmail(booking.get().getMemberEmail())
                .build();
    }
}



