package org.hotel.back.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.hotel.back.data.response.HotelImageDTO;
import org.json.simple.parser.ParseException;

import java.util.List;


public interface HotelImageCacheService {
	void save(HotelImageDTO dto);
	void delete(Long id);
	HotelImageDTO findById(Long id) throws JsonProcessingException;
}
