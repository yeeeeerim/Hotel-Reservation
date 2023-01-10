package com.example.demo.service;

import com.example.demo.domain.Board;
import com.example.demo.domain.HealthInfo;
import com.example.demo.domain.Reply;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.ReplyRepository;
import com.example.demo.repository.HealthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {
    @Autowired
    private final ReplyRepository replyRepository;
    @Autowired
    private final BoardRepository boardRepository;
    @Autowired
    private final HealthService healthService;
    @Autowired
    private HealthRepository healthRepository;

    public String replyWrite(Reply reply, HealthInfo healthInfo, Long id){
        HealthInfo findHealthInfo = healthRepository.findById(healthInfo.getId());
        Optional<Board> findBoard = boardRepository.findById(id);

        reply.setBoard(findBoard.get());
        reply.setHealthInfo(findHealthInfo);
    }

}
