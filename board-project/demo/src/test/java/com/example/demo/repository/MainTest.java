package com.example.demo.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
public class MainTest {

    @Autowired
    private BoardRepository boardRepository;


    @Test
    void test(){
        boardRepository.findAll(PageRequest.of(0,3)).forEach(System.out::println);
    }
}
