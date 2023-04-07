package org.hotel.back.data.dto;

import lombok.*;
import org.apache.tomcat.jni.Local;
import org.hotel.back.domain.Booking;
import org.hotel.back.domain.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Long roomId;
    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    private String memberEmail;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


    public Booking toEntity(){
        return Booking.builder()
                .roomId(this.roomId)
                .checkIn(this.checkIn)
                .checkOut(this.checkOut)
                .memberEmail(this.memberEmail)
                .build();
    }
    public static BookingDTO toDTO(Booking booking){
        return BookingDTO.builder()
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .memberEmail(booking.getMemberEmail())
                .createdAt(booking.getCreatedAt())
                .modifiedAt(booking.getModifiedAt())
                .build();
    }


}
