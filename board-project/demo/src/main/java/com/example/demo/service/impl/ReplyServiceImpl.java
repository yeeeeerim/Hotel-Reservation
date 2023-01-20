package com.example.demo.service.impl;

import com.example.demo.data.request.ReplyRequestDTO;
import com.example.demo.data.response.ReplyResponseDTO;
import com.example.demo.domain.Reply;
import com.example.demo.repository.ReplyRepository;
import com.example.demo.service.ReplyService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReplyServiceImpl implements ReplyService {
    private ReplyRepository replyRepository;
    @Override
    public ReplyResponseDTO read(Long replyId){
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(RuntimeException::new);
        return ReplyResponseDTO.builder()
                .replyId(reply.getReplyId())
                .writer(reply.getWriter())
                .content(reply.getContent())
                .build();
    }
    @Override
    public boolean saveReply(ReplyRequestDTO replyRequestDTO) {
        replyRepository.save(ReplyRequestDTO.toEntity(replyRequestDTO));
        return false;
    }
    @Override
    public boolean deleteReply(Long replyId) {
        replyRepository.deleteById(replyId);
        return false;
    }

    @Override
    public List<Reply> replyList(){
        List<Reply>list=replyRepository.findAll();
        return list;
    }
}
