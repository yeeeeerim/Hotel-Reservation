package org.hotel.batch.prog;


import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BookingConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job bookingJob(){
        return this.jobBuilderFactory.get("bookingJob")
                .incrementer(new RunIdIncrementer())
                .start(bookingStep())
                .build();
    }

    @Bean
    @JobScope
    public Step bookingStep(){
        return this.stepBuilderFactory.get("bookingStep")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("TEST 배치 프로그램");
                    return RepeatStatus.FINISHED;
                }).build();
    }


}
