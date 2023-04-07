package org.hotel.back.data.response;


import lombok.*;
import org.springframework.util.ObjectUtils;

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


        @Builder.Default
        private List<String> images = new ArrayList<>();

        private long totalRating;


        public double getTotalRating(){
           long rating = 0;

           if(!reviewResponseDTO.isEmpty()){


                   for (ReviewResponseDTO dto : reviewResponseDTO) {
                           if(dto.getRating().longValue() == 0) return 0;
                           if(!ObjectUtils.isEmpty(dto.getRating())) rating += dto.getRating();
                   }
                   return rating/reviewResponseDTO.size();
           }
           return 0;
        }
}
