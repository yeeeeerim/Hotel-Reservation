package org.hotel.back.data.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HotelImageDTO {
	@Builder.Default
	private List<String> name=new ArrayList<>();
	private Long hotelId;
}
