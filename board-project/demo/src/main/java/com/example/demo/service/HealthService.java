package com.example.demo.service;

import com.example.demo.data.request.HealthRequestDTO;
import com.example.demo.data.response.HealthResponseDTO;


public interface HealthService {

    public HealthResponseDTO read(Long id);

    public boolean saveHealth(HealthRequestDTO healthRequestDTO);

    public boolean updateHealth(HealthRequestDTO healthRequestDTO);

    public boolean deleteHealth(Long id);
}
