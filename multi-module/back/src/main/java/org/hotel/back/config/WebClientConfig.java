package org.hotel.back.config;


import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class WebClientConfig {

        @PersistenceContext
        private EntityManager em;

        @Bean
        public WebClient webClient(){
            return WebClient.builder()
                    .baseUrl("http://localhost:8090")
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();
        }

        @Bean
        public JPAQueryFactory jpaQueryFactory(){
                return new JPAQueryFactory(em);
        }

}
