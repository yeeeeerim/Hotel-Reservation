package org.hotel.back.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@ToString
public class HotelListResponseDTO {
	Long id;
	String hotelName;
	String cityName;
	String hotelImage;

	public HotelListResponseDTO(Hotel hotel){
		this.id=hotel.getId();
		this.hotelName=hotel.getHotelName();
		this.cityName=hotel.getCityName();
	}
}
