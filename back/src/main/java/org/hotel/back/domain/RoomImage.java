package org.hotel.back.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int roomImageId;
    int roomId;
    int uuid;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    int roomImageName;

}
