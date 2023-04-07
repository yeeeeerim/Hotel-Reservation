package org.hotel.back.data.response;

import lombok.*;
import org.hotel.back.data.dto.BookingDTO;
import org.hotel.back.data.dto.MemberDTO;
import org.hotel.back.data.request.BookingRequestDTO;
import org.hotel.back.domain.Booking;

import java.time.LocalDateTime;
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
    private Boolean deleted;
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

    public BookingDTO bookingDTO(LocalDateTime checkIn, LocalDateTime checkOut){
        return BookingDTO.builder()
                .roomId(this.roomId)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .memberEmail(this.memberEmail)
                .deleted(true)
                .build();
    }
}



