package org.hotel.back.service;

import org.hotel.back.domain.Hotel;
import org.hotel.back.data.request.HotelRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface HotelService {
    public boolean write(HotelRequestDTO hotelRequestDTO);
    public Page<Hotel> hotelList(Pageable pageable);
    public Hotel hotelDetail(Long id);
    public boolean  hotelDelete(Long id);
    public boolean hotelUpdate(HotelRequestDTO hotelRequestDTO);

}
