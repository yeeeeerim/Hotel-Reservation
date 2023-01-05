package com.example.demo.service;

import com.example.demo.data.request.HealthInfoRequestDTO;
import com.example.demo.data.response.HealthInfoResponseDTO;
import com.example.demo.domain.HealthInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HealthInfoService {


    public HealthInfoResponseDTO read(Long id);
    public boolean saveHealth(HealthInfoRequestDTO healthInfoRequestDTO);
    public boolean updateHealth(HealthInfoRequestDTO healthInfoRequestDTO);
    public boolean deleteHealth(Long id);

    Page<HealthInfo> healthInfoList(Pageable pageable); //페이징 처리를 위한 구문

}
