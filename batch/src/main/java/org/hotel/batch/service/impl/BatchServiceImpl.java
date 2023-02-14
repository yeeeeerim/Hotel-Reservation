package org.hotel.batch.service.impl;


import lombok.RequiredArgsConstructor;
import org.hotel.batch.service.BatchService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BatchServiceImpl implements BatchService {


    @Scheduled(initialDelay = 1, fixedDelay = 3000)
    public void test(){
        System.out.println("TEst 출력");
    }


}
