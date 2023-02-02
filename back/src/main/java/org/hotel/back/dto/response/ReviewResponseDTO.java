package org.hotel.back.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.Review;
import org.hotel.back.dto.request.ReviewRequestDTO;

import java.time.LocalDateTime;
@Getter
@Setter
public class ReviewResponseDTO {
    Long id;
    String reviewContent;
    String reviewWriter;
    Long hotelId;
    public static Review toEntity(ReviewRequestDTO dto){
        return Review.builder()
                .id(dto.getId())
                .reviewContent(dto.getReviewContent())
                .reviewWriter(dto.getReviewWriter())
                .build();
    }
    public ReviewResponseDTO(Review review){
        this.id=review.getId();
        this.reviewContent=review.getReviewContent();
        this.reviewWriter=review.getReviewWriter();
        this.hotelId=review.getHotel().getId();

    }
}
