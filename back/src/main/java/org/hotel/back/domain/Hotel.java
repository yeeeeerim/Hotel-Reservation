package org.hotel.back.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Hotel {
    int hotel_id;
    String hotel_name;
    String city_name;
    String tell_number;
    double latitude;
    double longitude;


}
