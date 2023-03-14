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

        private HotelResponseDTO hotelResponseDTO;

        private List<ReviewResponseDTO> reviewResponseDTO;


        private List<String> images = new ArrayList<>();

        private long totalRating;


        public double getTotalRating(){
           long rating = 0;

           if(!reviewResponseDTO.isEmpty()){
                   for (ReviewResponseDTO dto : reviewResponseDTO) {
                           rating += dto.getRating();
                   }
                   return rating/reviewResponseDTO.size();
           }
           return 0;
        }
}
