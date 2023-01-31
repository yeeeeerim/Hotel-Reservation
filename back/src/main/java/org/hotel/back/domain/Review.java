package org.hotel.back.domain;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDateTime;
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Review {
    int review_id;
    int hotel_id;
    String review_content;
    LocalDateTime created_at;
    LocalDateTime modified_at;
    String review_writer;
}
