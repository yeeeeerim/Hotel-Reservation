package com.back.bookingmodule.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@ToString
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email")
    private Member member;

    @Column(nullable = false)
    private LocalDateTime checkIn;
    @Column(nullable = false)
    private LocalDateTime check_out;

}
