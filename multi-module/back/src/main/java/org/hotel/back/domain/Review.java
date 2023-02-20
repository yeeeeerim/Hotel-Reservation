package org.hotel.back.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Review extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String reviewContent;
    String reviewWriter;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    Hotel hotel;

    public void updateReview(String reviewContent){
        this.reviewContent=reviewContent;
    }
}
