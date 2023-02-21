package com.back.bookingmodule.data;


import com.back.bookingmodule.domain.Booking;
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

    private String checkIn;

    private String checkOut;

    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


    public Booking toEntity(){
            return Booking.builder()
                    .checkIn(this.checkIn)
                    .checkOut(this.checkOut)
                    .status(this.status)
                    .build();
    }
    public static BookingDTO toDTO(Booking booking){
            return BookingDTO.builder()
                    .checkIn(booking.getCheckIn())
                    .checkOut(booking.getCheckOut())
                    .status(booking.getStatus())
                    .createdAt(booking.getCreatedAt())
                    .modifiedAt(booking.getModifiedAt())
                    .build();
    }


}
