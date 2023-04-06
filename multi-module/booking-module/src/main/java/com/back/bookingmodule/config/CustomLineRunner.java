package com.back.bookingmodule.config;


import com.back.bookingmodule.repository.HotelRepository;
import com.back.bookingmodule.service.BatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomLineRunner implements CommandLineRunner {

    private final EntityManager em;

    private final BatchService batchService;

    @Override
    public void run(String... args) throws Exception {
       Long count= 0L;
        try{
             count =  em.createQuery("SELECT count(h) FROM Hotel h",Long.class)
                    .getSingleResult();
        }catch (NoResultException resultException){
            log.error("The hotel table does not exist.");
        }

       if(count > 0){
           log.info("Hotel data exists");
       }else{
           log.info("Hotel data does not exist");
           log.debug("Hotel DATA INSERT RUNNING>>>>>");
           batchService.hotelJob();
       }
    }
}
