package com.example.demo.service;

import com.example.demo.data.request.HealthRequestDTO;
import com.example.demo.data.response.HealthResponseDTO;
import com.example.demo.domain.HealthInfo;

import java.util.List;


public interface HealthService {

    public HealthResponseDTO read(Long id);

    public boolean saveHealth(HealthRequestDTO healthRequestDTO);

    public boolean updateHealth(HealthRequestDTO healthRequestDTO);

    public boolean deleteHealth(Long id);

    List<HealthInfo> HealthList();
    public HealthInfo healthInfoDetail(Long id);
}
