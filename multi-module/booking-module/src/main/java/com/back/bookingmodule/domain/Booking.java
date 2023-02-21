package com.back.bookingmodule.domain;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Booking {      //TODO: 실제로 삭제가 아닌 값을 넘길 예정임

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email")
    private Member member;

    @Column(nullable = false)
    private String checkIn;
    @Column(nullable = false)
    private String checkOut;

    @Enumerated(EnumType.STRING)
    private Status status;



    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;


    public void changeBooking(String checkIn,String checkOut){
            this.checkIn = checkIn;
            this.checkOut = checkOut;
    }

}
