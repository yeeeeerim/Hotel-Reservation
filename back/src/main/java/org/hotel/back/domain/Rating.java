package org.hotel.back.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Rating {
    int rating_id;
    int hotel_id;
    String rating_content;
}
