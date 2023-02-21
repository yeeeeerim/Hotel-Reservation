package org.hotel.back.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Rating extends BaseTimeEntity{ //TODO: 수정요망
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ratingId;
    int hotelId;
    String ratingContent;
}
