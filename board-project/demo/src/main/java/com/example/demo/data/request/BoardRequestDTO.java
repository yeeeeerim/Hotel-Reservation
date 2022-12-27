package com.example.demo.data.request;


import com.example.demo.data.response.BoardResponseDTO;
import com.example.demo.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class BoardRequestDTO {

    private Long id;
    private String title;
    private String content;


    public static Board toEntity(BoardRequestDTO dto){
        return Board.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }
}
