package com.example.demo.service;

import com.example.demo.domain.HealthInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface HomeService {
    public void write(HealthInfo healthInfo);

    Page<HealthInfo> healthList(Pageable pageable);

}
