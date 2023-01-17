package com.example.demo.controller;

import com.example.demo.domain.HealthInfo;
import com.example.demo.domain.Reply;
import com.example.demo.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class ReplyController {
    @Autowired
    private final ReplyService replyService;

    public String replyWrite(@ModelAttribute Reply reply, Long boardId
            , HealthInfo healthInfo){
        return replyService.replyWrite(reply, healthInfo, boardId);
    }
}
