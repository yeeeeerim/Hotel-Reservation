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
public class RoomImage {
    int room_image_id;
    int room_id;
    int uuid;
    LocalDateTime created_at;
    LocalDateTime modified_at;
    int room_image_name;

}
