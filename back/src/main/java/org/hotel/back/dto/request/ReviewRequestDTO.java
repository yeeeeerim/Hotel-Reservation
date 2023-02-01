package org.hotel.back.dto.request;

import lombok.*;
import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.Review;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDTO {

    Long id;
    String reviewContent;
    String reviewWriter;
    Hotel hotel;
    public static Review toEntity(ReviewRequestDTO dto){
        return Review.builder()
                .reviewContent(dto.getReviewContent())
                .reviewWriter(dto.getReviewWriter())
                .hotel(dto.getHotel())
                .build();
    }
}
