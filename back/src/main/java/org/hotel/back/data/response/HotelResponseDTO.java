package org.hotel.back.data.response;

import lombok.*;
import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.HotelImage;
import org.hotel.back.domain.Review;

import java.util.ArrayList;
import java.util.List;

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
    List<Review> reviews = new ArrayList<>();
    String address;
    List<HotelImage>hotelImages =new ArrayList<>();

    public HotelResponseDTO(Hotel hotel){
        this.id=hotel.getId();
        this.hotelName=hotel.getHotelName();
        this.cityName=hotel.getCityName();
        this.tellNumber=hotel.getTellNumber();
        this.latitude=hotel.getLatitude();
        this.longitude=hotel.getLongitude();
        this.hotelImages=hotel.getHotelImages();
        this.reviews=hotel.getReviews();
    }


}
