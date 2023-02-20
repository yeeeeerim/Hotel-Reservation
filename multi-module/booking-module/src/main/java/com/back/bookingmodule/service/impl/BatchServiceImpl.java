package com.back.bookingmodule.service.impl;


import com.back.bookingmodule.service.BatchService;
import lombok.RequiredArgsConstructor;
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
