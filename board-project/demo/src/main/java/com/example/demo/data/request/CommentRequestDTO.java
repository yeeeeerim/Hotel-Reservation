package com.example.demo.data.request;

import com.example.demo.domain.Reply;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDTO {
    private Long id;
    private String content;
    public static Reply toEntity(CommentRequestDTO dto){
        return Reply.builder()
                .replyId(dto.getId())
                .content(dto.getContent())
                .build();
    }

    public Long getId() {
        return this.id;
    }
    public String getContent() {
        return this.content;
    }
}
