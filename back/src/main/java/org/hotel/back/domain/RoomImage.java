package org.hotel.back.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomImage extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;


    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
    //C:\lecture_homework\oneToy\proj\STUDY-GROUP-ACTIVITY\back\temp
}
