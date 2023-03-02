package org.hotel.back.data.response;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HotelAndReviewDTO {

        public HotelResponseDTO hotelResponseDTO;

        public List<ReviewResponseDTO> reviewResponseDTO;


        public List<String> images = new ArrayList<>();
}
