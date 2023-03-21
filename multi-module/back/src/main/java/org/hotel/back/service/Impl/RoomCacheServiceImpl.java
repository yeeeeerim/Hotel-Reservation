package org.hotel.back.service.Impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hotel.back.data.response.RoomDTO;
import org.hotel.back.service.RoomCacheService;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomCacheServiceImpl implements RoomCacheService {

    private static final String KEY = "ROOM_DATA_KEY";

    private final ObjectMapper objectMapper;

    private final RedisTemplate<String,Object> redisTemplate;
    private HashOperations<String,String,String> hashOperations;


    @PostConstruct
    public void init(){
        this.hashOperations = redisTemplate.opsForHash();
    }


    public void save(RoomDTO dto){
        if(Objects.isNull(dto) || Objects.isNull(dto.getId())){
            log.error("Required values must not be null");
            return;
        }
        try{
            hashOperations.put(KEY, dto.getId().toString(),serializeDTO(dto));
            log.info("CACHE 저장 {}",dto.getId());
        } catch (JsonProcessingException e) {
            log.error("ERROR LOG {}",e.getMessage());
        }
    }

    public RoomDTO findById(Long id){
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

    @Override
    public void delete(Long id) {
        hashOperations.delete(KEY,String.valueOf(id));
        log.info("DELETE REDIS DATA {}",id);
    }


    /**
     * @apiNote RoomDTO 데이터를 받아 json으로 변경
     * */
    private String serializeDTO(RoomDTO dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }

    /**
     * @apiNote JSON 데이터를 받아 RoomDTO로 변경
     * */
    private RoomDTO deserializeDTO(String json) throws JsonProcessingException {
        return objectMapper.readValue(json,RoomDTO.class);
    }



}
