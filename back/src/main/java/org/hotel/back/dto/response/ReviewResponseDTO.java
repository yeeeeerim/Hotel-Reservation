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
    long id;
    String reviewContent;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    String reviewWriter;
    Long hotelId;
    public static Review toEntity(ReviewRequestDTO dto){
        return Review.builder()
                .id(dto.getId())
                .reviewContent(dto.getReviewContent())
                .reviewWriter(dto.getReviewWriter())
                .createdAt(dto.getCreatedAt())
                .modifiedAt(dto.getModifiedAt())
                .build();
    }
    public ReviewResponseDTO(Review review){
        this.id=review.getId();
        this.reviewContent=review.getReviewContent();
        this.createdAt=review.getCreatedAt();
        this.modifiedAt=review.getModifiedAt();
        this.reviewWriter=review.getReviewWriter();
        this.hotelId=review.getHotel().getId();

    }
}
