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

        private ReviewResponseDTO reviewResponseDTO;


        @Builder.Default
        private List<String> images = new ArrayList<>();

        private long totalRating;


}
