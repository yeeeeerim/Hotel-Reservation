package org.hotel.back.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hotel.back.domain.Hotel;
import org.hotel.back.domain.RoomImage;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
@ToString
public class RoomResponseDTO {
    private Long id;

    private Set<String> roomImage = new HashSet<>();

    private String roomNumber;  //방번호

    private String roomClass;   //방등급

    private String roomPrice;   //방가격

    private String roomLimit;   //제한인원

    private String description; //방설명

    private long hotelId;

    private boolean checking;

}
