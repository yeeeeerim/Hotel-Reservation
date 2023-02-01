package org.hotel.back.dto.request;

import lombok.*;
import org.hotel.back.domain.Hotel;

@Builder
@Setter
@Getter
@AllArgsConstructor
@ToString
public class HotelRequestDTO {
    long id;
    String hotelName;
    String cityName;
    String tellNumber;
    String latitude;
    String longitude;

    public static Hotel toEntity(HotelRequestDTO hotelRequestDTO){
        return Hotel.builder()
                .id(hotelRequestDTO.getId())
                .hotelName(hotelRequestDTO.getHotelName())
                .cityName(hotelRequestDTO.getCityName())
                .tellNumber(hotelRequestDTO.getTellNumber())
                .latitude(hotelRequestDTO.getLatitude())
                .longitude(hotelRequestDTO.getLongitude())
                .build();
    }
}
