package com.example.demo.service.impl;

import com.example.demo.domain.HealthInfo;
import com.example.demo.repository.HealthRepository;
import com.example.demo.service.HomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HomeServiceImpl implements HomeService {
    private final HealthRepository healthRepository;

    @Override
    public void write(HealthInfo healthInfo) {

        healthRepository.save(healthInfo);
    }
    @Override
    public Page<HealthInfo> healthList(Pageable pageable) {
        return healthRepository.findAll(pageable);
    }

}
