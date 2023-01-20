package com.example.demo.data.response;

import com.example.demo.domain.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@Setter
@ToString
public class ReplyResponseDTO {
    private Long replyId;
    private String writer;
    private String content;

    public static Reply toEntity(ReplyResponseDTO replyResponseDTO){
        return Reply.builder()
                .replyId(replyResponseDTO.getReplyId())
                .writer(replyResponseDTO.getWriter())
                .content(replyResponseDTO.getContent())
                .build();
    }
}
