package com.example.demo.data.request;


import com.example.demo.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@AllArgsConstructor
@ToString
public class BoardRequestDTO {
//request pageNum size

    //response totalPage pageNum(현재페이지) 리스트(데이터)
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

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public static BoardRequestDTO of(String title, String content){
        return  new BoardRequestDTO(1L,title,content);
    }
}
