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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String reviewContent;
    @CreatedDate
    LocalDateTime createdAt;
    @LastModifiedDate
    LocalDateTime modifiedAt;
    String reviewWriter;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotelId")
    Hotel hotel;

    public void updateReview(String reviewContent){
        this.reviewContent=reviewContent;
    }
}
