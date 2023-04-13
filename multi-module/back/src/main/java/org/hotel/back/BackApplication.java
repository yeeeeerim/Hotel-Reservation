package org.hotel.back;

import org.hotel.back.domain.Gender;
import org.hotel.back.domain.Member;
import org.hotel.back.domain.MemberRole;
import org.hotel.back.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;


@SpringBootApplication
public class BackApplication {

    @Bean
    CommandLineRunner initDataSetUp(MemberRepository memberRepository, PasswordEncoder passwordEncoder){
            return args -> {
                memberRepository.save(Member.builder()
                        .email("owner@naver.com")
                        .tellNumber("01012345678")
                        .gender(Gender.MAN)
                        .roleSet(Set.of(MemberRole.ROLE_OWNER))
                        .nickName("닉네임")
                        .password(passwordEncoder.encode("1234"))
                        .build());
            };
    }


    public static void main(String[] args) {
        SpringApplication.run(BackApplication.class, args);
    }

}
