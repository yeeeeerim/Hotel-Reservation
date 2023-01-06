package com.example.demo.service.impl;

import com.example.demo.data.request.HealthRequestDTO;
import com.example.demo.data.response.HealthResponseDTO;
import com.example.demo.domain.HealthInfo;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.HealthRepository;
import com.example.demo.service.HealthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HealthServiceImpl implements HealthService {


    private final HealthRepository healthRepository;
    @Override
    public HealthResponseDTO read(Long id){
        HealthInfo healthInfo = healthRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        return HealthResponseDTO.builder()
                .id(healthInfo.getId())
                .brand_name(healthInfo.getBrand_name())
                .category(healthInfo.getCategory())
                .land_number(healthInfo.getLand_number())
                .road_number(healthInfo.getRoad_number())
                .build();
    }

    @Override
    public boolean saveHealth(HealthRequestDTO healthRequestDTO){
        healthRepository.save(healthRequestDTO.toEntity(healthRequestDTO));
        return false;
    }
    public boolean updateHealth(HealthRequestDTO healthRequestDTO){

        HealthInfo healthInfo = healthRepository.findById(healthRequestDTO.getId()).orElseThrow(RuntimeException::new);
        healthInfo.modifyHealthinfo(healthRequestDTO.getBrand_name(),healthRequestDTO.getLand_number()
                ,healthRequestDTO.getRoad_number(),healthRequestDTO.getCategory());

        try{
            healthRepository.save(healthInfo);
        }catch (Exception e){
            return false;
        }
        return true;
    };

    public boolean deleteHealth(Long id){
        healthRepository.deleteById(id);
        return true;
    };






}
