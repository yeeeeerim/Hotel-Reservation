package org.hotel.back.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HotelImage {
    int hotel_image_id;
    int room_id;
    int uuid;
    String created_at;
    String modified_at;
    String image_name;
}
