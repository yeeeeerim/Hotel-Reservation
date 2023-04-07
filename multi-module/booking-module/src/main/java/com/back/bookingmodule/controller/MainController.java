package com.back.bookingmodule.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("http://localhost:8080")
@RestController
@RequiredArgsConstructor
public class MainController {

//    private final Job job;
//    private final JobLauncher jobLauncher;
//
//
//    @GetMapping("/test")
//    public ResponseEntity<String> test() throws JobInstanceAlreadyCompleteException,
//            JobExecutionAlreadyRunningException,
//            JobParametersInvalidException,
//            JobRestartException {
//        JobParameters jobParameters = new JobParametersBuilder()
//                .toJobParameters();
//        jobLauncher.run(job,jobParameters);
//        return ResponseEntity.ok("test");
//    }


<<<<<<< HEAD
    @GetMapping("/test")
    public ResponseEntity<String> test() throws JobInstanceAlreadyCompleteException,
            JobExecutionAlreadyRunningException,
            JobParametersInvalidException,
            JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder()
                .toJobParameters();
        jobLauncher.run(job,jobParameters);
        return ResponseEntity.ok("test");
    }


=======
>>>>>>> 527d5079c141d5a2fb705066adbd502aa53ed9e1
    @GetMapping("/test2")
    public  ResponseEntity<String>  test2(){
        System.out.println("test2");
        return ResponseEntity.ok("SUCEEEE!!!!");
    }

    @GetMapping("/test3")
    public  ResponseEntity<String>  test3(){
        System.out.println("test3");
        return ResponseEntity.ok("SUCEEEE!!!!");
    }

}
