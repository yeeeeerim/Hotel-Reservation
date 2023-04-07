package org.hotel.back.data.dto;

import lombok.*;
import org.hotel.back.data.request.BookingRequestDTO;
import org.hotel.back.domain.Booking;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    private Long id;

    private Long roomId;

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    private String memberEmail;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private Boolean deleted;

    public Booking toEntity(){
        return Booking.builder()
                .id(this.id)
                .roomId(this.roomId)
                .checkIn(this.checkIn)
                .checkOut(this.checkOut)
                .memberEmail(this.memberEmail)
                .deleted(this.deleted)
                .build();
    }
    public static BookingDTO toDTO(Booking booking){
        return BookingDTO.builder()
                .roomId(booking.getRoomId())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .memberEmail(booking.getMemberEmail())
                .build();
    }

    public static BookingDTO bookingDTO(BookingRequestDTO dto, LocalDateTime checkIn, LocalDateTime checkOut, MemberDTO memberDTO){
        return BookingDTO.builder()
                .roomId(dto.getRoomId())
                .checkIn(checkIn)
                .checkOut(checkOut)
                .memberEmail(memberDTO.getEmail())
                .deleted(false)
                .build();
    }

}
