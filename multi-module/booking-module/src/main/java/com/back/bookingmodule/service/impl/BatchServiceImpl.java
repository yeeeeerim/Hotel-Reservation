package com.back.bookingmodule.service.impl;


import com.back.bookingmodule.domain.Member;
import com.back.bookingmodule.prog.BookingConfiguration;
import com.back.bookingmodule.prog.HotelCSVConfig;
import com.back.bookingmodule.repository.BookingRepository;
import com.back.bookingmodule.service.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class BatchServiceImpl implements BatchService {

    private final BookingRepository bookingRepository;

    private final BookingConfiguration bookingConfiguration;

    private final JobLauncher jobLauncher;

    private final HotelCSVConfig hotelCSVConfig;



    @Scheduled(initialDelay = 1000, fixedDelay = 12 * 60 * 60 * 1000)
    public void job() throws Exception {

        Map<String, JobParameter> confMap = new HashMap<>();
        confMap.put("time",new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(confMap);
        jobLauncher.run(bookingConfiguration.bookingJob(),jobParameters);

    }

    public void hotelJob() throws Exception {

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(hotelCSVConfig.csvItemWriterJob(), jobParameters);
    }


}
