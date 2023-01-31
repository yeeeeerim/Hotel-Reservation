package org.hotel.back.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int bookingId;
    int hotelId;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    int memberId;
    LocalDateTime checkIn;
    LocalDateTime check_out;

}
