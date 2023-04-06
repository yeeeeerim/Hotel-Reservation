package org.hotel.back.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email", insertable = false, updatable = false)
    private Member member;

    @JoinColumn(name = "hotel_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Hotel hotel;

    @JsonIgnore
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    @ManyToOne
    private Room room;

    @Column(nullable = false)
    private LocalDateTime checkIn;
    @Column(nullable = false)
    private LocalDateTime checkOut;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "member_email")
    private String memberEmail;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private Boolean deleted;
}
