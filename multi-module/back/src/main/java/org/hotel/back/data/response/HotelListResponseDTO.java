package org.hotel.back.data.response;

import lombok.Builder;
import lombok.Getter;
import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
public class HotelListResponseDTO {
	Long id;
	String hotelName;
	String cityName;
	Double ratingAvg;

	@Builder.Default
	List<String>hotelImages=new ArrayList<>();

	public HotelListResponseDTO(Hotel hotel, Double avg){
		this.id=hotel.getId();
		this.hotelName=hotel.getHotelName();
		this.cityName=hotel.getCityName();
		this.ratingAvg=avg;
	}
}
