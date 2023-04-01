package com.back.bookingmodule.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hotelName;
    private String cityName;
    private String tellNumber;
    private String latitude;
    private String longitude;


    @CreatedBy
    private String writer;
    private String address;
}
