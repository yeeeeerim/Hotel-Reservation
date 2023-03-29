package org.hotel.back.config.booking;

import org.hotel.back.data.response.RoomDTO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Map;

@Component
@ComponentScan("org.hotel.back.config.booking")
public class RoomDtoConverter implements Converter<Map<String, Object>, RoomDTO> {

    @Override
    public RoomDTO convert(Map<String, Object> source) {
        RoomDTO roomDto = new RoomDTO();
        roomDto.setId(((BigInteger) source.get("id")).longValue());
        roomDto.setRoomNumber((String) source.get("roomNumber"));
        roomDto.setRoomClass((String) source.get("roomClass"));
        roomDto.setRoomPrice((String) source.get("roomPrice"));
        roomDto.setRoomLimit((String) source.get("roomLimit"));
        roomDto.setDescription((String) source.get("description"));

        return roomDto;
    }
}
