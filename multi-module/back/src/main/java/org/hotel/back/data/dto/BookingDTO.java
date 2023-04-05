package org.hotel.back.data.dto;

import lombok.*;
import org.apache.tomcat.jni.Local;
import org.hotel.back.data.request.BookingRequestDTO;
import org.hotel.back.domain.Booking;
import org.hotel.back.domain.Member;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private String roomNumber;


    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    private String memberEmail;


    public Booking toEntity(){
        return Booking.builder()
                .roomNumber(this.roomNumber)
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
                .build();
    }

    public static BookingDTO bookingDTO(BookingRequestDTO dto, LocalDateTime checkIn, LocalDateTime checkOut, MemberDTO memberDTO){
        return BookingDTO.builder()
                .checkIn(checkIn)
                .checkOut(checkOut)
                .memberEmail(memberDTO.getEmail())
                .build();
    }


}
