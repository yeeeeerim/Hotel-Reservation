package com.example.demo.data.response;


import com.example.demo.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class BoardResponseDTO {

    private Long id;
    private String title;
    private String content;


    public static Board toEntity(BoardResponseDTO boardResponseDTO){
        return Board.builder()
                .id(boardResponseDTO.getId())
                .title(boardResponseDTO.getTitle())
                .content(boardResponseDTO.getContent())
                .build();
    }
}
