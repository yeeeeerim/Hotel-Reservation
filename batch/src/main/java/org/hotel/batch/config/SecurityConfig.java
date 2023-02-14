package org.hotel.batch.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * TODO: Batch 기능이 완료되면 그떄 여기 수정 예정 일단 권한 다 열어놈
     *
     * */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(auth -> {
                auth.anyRequest().permitAll();
        });

        return http.build();

    }


}
