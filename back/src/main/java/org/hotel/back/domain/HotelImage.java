package org.hotel.back.domain;

import lombok.*;

import javax.persistence.*;

import java.util.UUID;
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HotelImage {
    @Id
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    @ToString.Exclude
    private Hotel hotel;

}
