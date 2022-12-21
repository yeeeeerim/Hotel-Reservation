package com.example.demo.repository;

import com.example.demo.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void test(){
        boardRepository.save(Board.builder()
                        .title("이건 제목입니다")
                        .content("이건 내용입니다")
                .build());
    }
}