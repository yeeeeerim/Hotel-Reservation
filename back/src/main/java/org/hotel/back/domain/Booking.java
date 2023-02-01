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
@Table(name = "Booking")
public class Booking {

    //hotel 하고 manytone,member하고도 manytoone
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    @ManyToOne
    @JoinColumn(name = "hotelId")
    private Long hotelId;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime modifiedAt;
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Long memberId;
    @Column(nullable = false)
    private LocalDateTime checkIn;
    @Column(nullable = false)
    private LocalDateTime check_out;

    public void modifyBooking(Long hotelId, Long memberId, LocalDateTime checkIn){
        this.hotelId = hotelId;
        this.modifiedAt = LocalDateTime.now();
        this.memberId = memberId;
        this.checkIn = LocalDateTime.now();
        this.check_out = checkIn.plusDays(1);

    }
}
