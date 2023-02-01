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
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime modifiedAt;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_email")
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
