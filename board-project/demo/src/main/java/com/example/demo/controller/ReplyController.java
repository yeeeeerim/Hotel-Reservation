package com.example.demo.controller;

import com.example.demo.domain.Reply;
import com.example.demo.repository.ReplyRepository;
import com.example.demo.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyRepository replyRepository;
    private final ReplyService replyService;

    @GetMapping("/reply/write")
    public String replyInfo(){ return "replysave";}

    @GetMapping("/reply")
    public String replyWrite(Model model, Long id){
        model.addAttribute("replyInfo",
                replyService.replyList());
        return "replydetail";
    }
    @PostMapping("/reply/write/post")
    public String replyWrite(Reply reply){
        replyRepository.save(Reply.builder()
                .replyId(reply.getReplyId())
                .writer(reply.getWriter())
                .content(reply.getContent())
                .build());
        return "redirect:/detail";
    }
}
