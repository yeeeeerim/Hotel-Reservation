package org.hotel.back.config;


import lombok.RequiredArgsConstructor;
import org.hotel.back.service.security.MemberDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final MemberDetailsService memberDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests(auth ->
                auth.anyRequest().permitAll()
        ).csrf()
                .disable()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/main")
                .failureUrl("/login?err=e")
                .and()
                .userDetailsService(memberDetailsService)
                                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
    }
}
