package com.back.bookingmodule.data;


import com.back.bookingmodule.domain.booking.Booking;
import com.back.bookingmodule.domain.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    private Status status;
    private String memberEmail;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


    public Booking toEntity(){
            return Booking.builder()
                    .checkIn(this.checkIn)
                    .checkOut(this.checkOut)
                    .status(this.status)
                    .memberEmail(this.memberEmail)
                    .build();
    }
    public static BookingDTO toDTO(Booking booking){
            return BookingDTO.builder()
                    .checkIn(booking.getCheckIn())
                    .checkOut(booking.getCheckOut())
                    .status(booking.getStatus())
                    .memberEmail(booking.getMemberEmail())
                    .createdAt(booking.getCreatedAt())
                    .modifiedAt(booking.getModifiedAt())
                    .build();
    }


}
