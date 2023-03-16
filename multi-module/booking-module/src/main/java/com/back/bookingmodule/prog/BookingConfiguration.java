package com.back.bookingmodule.prog;


import com.back.bookingmodule.domain.Booking;
import com.back.bookingmodule.prog.tasklet.BookingTasklet;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BookingConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final EntityManagerFactory entityManagerFactory;

    private final BookingTasklet bookingTasklet;

    @Bean
    public Job bookingJob() throws Exception {
        return this.jobBuilderFactory.get("bookingJob")
                .incrementer(new UniqueRunIdIncrementer())
                .start(bookingJPAStep())
                .build();
    }



    @Bean
    @JobScope
    public Step bookingJPAStep() throws Exception {
        return this.stepBuilderFactory
                .get("bookingDeleting")
                .tasklet(bookingTasklet)
                .build();
    }

    public ItemWriter<? super Booking> itemWriter(){

        return items -> {
            items.forEach(booking -> {
                if(LocalDateTime.now().isBefore(booking.getCheckOut())){
                         booking.changeDeleting();
                }
            });
        };
    }


    public ItemReader<? extends Booking> bookingItemReader() throws Exception{
        JpaPagingItemReader<Booking> bookingJpaPagingItemReader =
                    new JpaPagingItemReaderBuilder<Booking>()
                            .queryString("select b from Booking b")
                            .entityManagerFactory(entityManagerFactory)
                            .pageSize(100)
                            .name("bookingItemReader")
                            .build();

        return bookingJpaPagingItemReader;

    }


}
