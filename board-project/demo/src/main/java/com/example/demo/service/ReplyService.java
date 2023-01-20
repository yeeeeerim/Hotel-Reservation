package com.example.demo.service;

import com.example.demo.data.request.ReplyRequestDTO;
import com.example.demo.data.response.ReplyResponseDTO;
import com.example.demo.domain.Reply;

import java.util.List;

public interface ReplyService {

    public ReplyResponseDTO read(Long replyId);
    public boolean saveReply(ReplyRequestDTO replyRequestDTO);

    public boolean deleteReply(Long replyId);
    List<Reply> replyList();
}
