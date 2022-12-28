package com.example.demo.service;

import com.example.demo.data.request.BoardRequestDTO;
import com.example.demo.data.response.BoardResponseDTO;

public interface BoardService {



    public BoardResponseDTO read(Long id);
    //TODO: 재우
    public boolean saveBoard(BoardRequestDTO boardRequestDTO);
    //TODO: 우람
    public BoardResponseDTO updateBoard(BoardRequestDTO boardRequestDTO);
    //TODO: 예림

    public boolean deleteBoard(Long boardRequestDTO);
    //TOdO: 영진

}
