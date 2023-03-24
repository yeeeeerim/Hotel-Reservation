package org.hotel.back.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hotel.back.data.response.HotelImageDTO;
import org.hotel.back.data.response.RoomDTO;
import org.hotel.back.domain.Hotel;
import org.hotel.back.repository.HotelImageRepository;
import org.hotel.back.repository.HotelRepository;
import org.hotel.back.service.HotelImageCacheService;
import org.hotel.back.service.HotelService;
import org.json.simple.parser.ParseException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelImageCacheServiceImpl implements HotelImageCacheService {


	private static final String KEY="HOTEL_IMAGE_DATA_KEY";
	private final ObjectMapper objectMapper;
	private final RedisTemplate<String,Object> redisTemplate;
	private HashOperations<String, String, String> hashOperations;
	@PostConstruct
	public void init(){
		this.hashOperations = redisTemplate.opsForHash();
	}


	@Override
	public void save(HotelImageDTO dto) {
		if(Objects.isNull(dto) || Objects.isNull(dto.getHotelId())){
			log.error("Required values must not be null");
			return;
		}
		try{
			hashOperations.put(KEY, dto.getHotelId().toString(),serializeDTO(dto));
			log.info("CACHE 저장 {}",dto.getHotelId());
		} catch (JsonProcessingException e) {
			log.error("ERROR LOG {}",e.getMessage());
		}
	}

	@Override
	public void delete(Long id) {
		hashOperations.delete(KEY,id.toString());
		log.info("DELETE REDIS DATA {}",id);
	}

	@Override
	public HotelImageDTO findById(Long id) throws JsonProcessingException {
		if(Objects.isNull(id)){
			log.error("Required values must not be null");
			return null;
		}

		try{
			String data =hashOperations.get(KEY,id.toString());
			log.info("FIND DATA {} ", data != null ? data : "EMPTY");

			if(StringUtils.hasText(data)){
				return deserializeDTO(data);
			}else{
				return null;
			}

		} catch (JsonProcessingException e) {
			log.error("ERROR LOG {}",e.getMessage());
			return null;
		}
	}

	/**
	 * @apiNote RoomDTO 데이터를 받아 json으로 변경
	 * */
	private String serializeDTO(HotelImageDTO dto) throws JsonProcessingException {
		return objectMapper.writeValueAsString(dto);
	}

	/**
	 * @apiNote JSON 데이터를 받아 RoomDTO로 변경
	 * */
	private HotelImageDTO deserializeDTO(String json) throws JsonProcessingException {
		return objectMapper.readValue(json,HotelImageDTO.class);
	}

}
