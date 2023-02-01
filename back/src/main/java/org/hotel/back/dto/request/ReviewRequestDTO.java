package org.hotel.back.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
public class ReviewRequestDTO {

    long id;
    String reviewContent;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    String reviewWriter;
    Hotel hotel;
    public static Review toEntity(ReviewRequestDTO dto){
        return Review.builder()
                .id(dto.getId())
                .reviewContent(dto.getReviewContent())
                .reviewWriter(dto.getReviewWriter())
                .createdAt(dto.getCreatedAt())
                .modifiedAt(dto.getModifiedAt())
                .build();
    }
}
