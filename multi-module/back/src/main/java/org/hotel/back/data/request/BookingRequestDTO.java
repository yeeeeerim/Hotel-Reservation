package org.hotel.back.data.request;

import lombok.*;
import org.hotel.back.domain.Booking;
import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
public class BookingRequestDTO {

    private String roomNumber;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;


}
