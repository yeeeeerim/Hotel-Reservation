package org.hotel.back.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HotelImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int hotel_image_id;
    int room_id;
    int uuid;
    String created_at;
    String modified_at;
    String image_name;

    public static void main(String[] args) {
    }
}
