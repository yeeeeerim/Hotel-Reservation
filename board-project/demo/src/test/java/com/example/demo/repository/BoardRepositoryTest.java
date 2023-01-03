package com.example.demo.repository;

import com.example.demo.data.request.BoardRequestDTO;
import com.example.demo.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void test(){
        IntStream.rangeClosed(0,5).forEach(value -> {
            boardRepository.save(Board.builder()
                    .title("이건 제목입니다"+value)
                    .content("이건 내용입니다"+value)
                    .build());
        });

        BoardRequestDTO boardRequestDTO = BoardRequestDTO.of("이건 제목임","이건 내용임");
    }

}