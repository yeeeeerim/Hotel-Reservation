package org.hotel.back.data.response;

import lombok.*;
import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.Review;
@Builder
@Setter
@AllArgsConstructor
@ToString
@Getter
public class HotelResponseDTO {
    Long id;
    String hotelName;
    String cityName;
    String tellNumber;
    String latitude;
    String longitude;
    Review review;

    public HotelResponseDTO(Hotel hotel){
        this.id=hotel.getId();
        this.hotelName=hotel.getHotelName();
        this.cityName=hotel.getCityName();
        this.tellNumber=hotel.getTellNumber();
        this.latitude=hotel.getLatitude();
        this.longitude=hotel.getLongitude();
    }
}
