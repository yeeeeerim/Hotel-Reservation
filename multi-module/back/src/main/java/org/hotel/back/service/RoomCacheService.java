package org.hotel.back.service;

import org.hotel.back.data.response.RoomDTO;

public interface RoomCacheService {

    public void save(RoomDTO dto);
    public RoomDTO findById(Long id);

    public void delete(Long id);
}
