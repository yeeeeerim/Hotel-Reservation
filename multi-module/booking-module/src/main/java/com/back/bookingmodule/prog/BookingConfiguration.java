package com.back.bookingmodule.prog;


import com.back.bookingmodule.domain.booking.Booking;
import com.back.bookingmodule.prog.tasklet.BookingTasklet;
import com.back.bookingmodule.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Configuration
@Slf4j
@EnableBatchProcessing
@RequiredArgsConstructor
public class BookingConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final EntityManagerFactory entityManagerFactory;
    private final BookingRepository bookingRepository;


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
                .<Booking,Booking>chunk(10)
                .reader(this.reader())
                .writer(this.writer())
                .build();
    }

    private ItemWriter<? super Booking> writer() {
        log.info("ITEMWRITER RUN+++++++");
        return items -> {
            items.forEach(booking -> {
                bookingRepository.updateDelete(booking.getId());
            });
        };
    }


    public ItemReader<? extends Booking> reader() throws Exception {
            log.info("ITEMREADER RUN+++++++");
           JpaPagingItemReader itemReader= new JpaPagingItemReaderBuilder<Booking>()
                   .name("bookingItemReader")
                   .entityManagerFactory(entityManagerFactory)
                   .pageSize(10)
                   .queryString("select b from Booking b where b.checkOut < :date and b.deleted = false")
                   .parameterValues(Collections.singletonMap("date",LocalDateTime.now()))
                   .build();

           itemReader.afterPropertiesSet();

           return itemReader;
    }



}
