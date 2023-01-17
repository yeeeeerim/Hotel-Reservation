package com.example.demo.service;

import com.example.demo.domain.HealthInfo;
import java.util.List;

public interface HomeService {
    public void write(HealthInfo healthInfo);
    public List<HealthInfo> HealthList();

}
