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
    int booking_id;
    int hotel_id;
    LocalDateTime created_at;
    LocalDateTime modified_at;
    int member_id;
    LocalDateTime check_in;
    LocalDateTime check_out;

}
