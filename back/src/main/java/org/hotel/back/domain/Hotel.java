package org.hotel.back.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int hotelId;
    String hotelName;
    String cityName;
    String tellNumber;
    double latitude;
    double longitude;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "hotelId")
    private List<Booking> bookingList = new ArrayList<>();
}
