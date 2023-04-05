package org.hotel.back.data.request;

import lombok.*;
import org.apache.tomcat.jni.Local;
import org.hotel.back.domain.Booking;
import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.Member;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingRequestDTO {

    private String roomNumber;
    @Pattern(regexp="^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$",message = "들어온 날짜 형식이 일치하지 않음 yyyy-MM-dd")
    private String checkIn;
    @Pattern(regexp="^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$",message = "들어온 날짜 형식이 일치하지 않음 yyyy-MM-dd")
    private String checkOut;

    private Member member;


}
