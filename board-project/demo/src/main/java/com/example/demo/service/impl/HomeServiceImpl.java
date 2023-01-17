package com.example.demo.service.impl;

import com.example.demo.domain.HealthInfo;
import com.example.demo.repository.HealthRepository;
import com.example.demo.service.HomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HomeServiceImpl implements HomeService {
    HealthRepository healthRepository;

    @Override
    public void write(HealthInfo healthInfo) {

        healthRepository.save(healthInfo);
    }

    @Override
    public List<HealthInfo> HealthList() {
        List<HealthInfo>list=healthRepository.findAll();
        return list;
    }
}
