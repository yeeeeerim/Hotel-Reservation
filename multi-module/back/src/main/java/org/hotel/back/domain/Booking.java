package org.hotel.back.domain;

import ch.qos.logback.core.status.Status;
import lombok.*;
import org.hotel.back.domain.Member;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
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
    @JoinColumn(name = "member_email", insertable = false, updatable = false)
    private Member member;


    @Column(nullable = false)
    private LocalDate checkIn;
    @Column(nullable = false)
    private LocalDate checkOut;


//    @Enumerated(EnumType.STRING)
//    private Status status;

    @Column(name = "member_email")
    private String memberEmail;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    @ToString.Exclude
    private Room room;

}