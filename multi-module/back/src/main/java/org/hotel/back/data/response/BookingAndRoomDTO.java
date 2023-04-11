package org.hotel.back.data.response;

import lombok.Builder;
import lombok.Data;
import org.hotel.back.data.dto.BookingDTO;

import java.util.List;


@Data
@Builder
public class BookingAndRoomDTO {

    BookingDTO bookingDTO;

    RoomDTO roomDTO;

}
