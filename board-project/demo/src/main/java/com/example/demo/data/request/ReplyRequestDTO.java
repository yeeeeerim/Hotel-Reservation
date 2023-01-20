package com.example.demo.data.request;

import com.example.demo.domain.Reply;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReplyRequestDTO {
    private Long replyId; // 댓글 번호
    private String writer;
    private String content;

    public static Reply toEntity(ReplyRequestDTO dto){
        return Reply.builder()
                .replyId(dto.getReplyId())
                .writer(dto.getWriter())
                .content(dto.getContent())
                .build();
    }
}
