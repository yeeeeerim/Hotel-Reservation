<<<<<<< HEAD
//package org.hotel.back.controller;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//class HotelControllerTest {
//
//    @Test
//    void hotelList() {
//    }
//}
=======
package org.hotel.back.controller;

import org.assertj.core.api.Assertions;
import org.hotel.back.domain.Hotel;
import org.hotel.back.service.HotelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class HotelControllerTest {


    @Autowired
    HotelService hotelService;

    @Test
    void hotelList() {


    }
}
>>>>>>> 61c0dc166be82ee55c2658f83a9f500cd6060f80
