package org.hotel.back.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Room {
    int room_id;
    int hotel_id;
    int room_number;
    int room_class;
    int room_price;
    int room_limit;
    String description;
}
