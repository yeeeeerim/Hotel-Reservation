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
    private Hotel hotel;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime modifiedAt;
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
    @Column(nullable = false)
    private LocalDateTime checkIn;
    @Column(nullable = false)
    private LocalDateTime check_out;

    public void modifyBooking(Hotel hotel, Member member, LocalDateTime checkIn){
        this.hotel = hotel;
        this.modifiedAt = LocalDateTime.now();
        this.member = member;
        this.checkIn = LocalDateTime.now();
        this.check_out = checkIn.plusDays(1);

    }
}
