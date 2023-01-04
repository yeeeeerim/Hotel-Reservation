package com.example.demo.service;

import com.example.demo.data.request.BoardRequestDTO;
import com.example.demo.data.response.BoardResponseDTO;
import com.example.demo.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {



    public BoardResponseDTO read(Long id);
    //TODO: 재우
    public boolean saveBoard(BoardRequestDTO boardRequestDTO);
    //TODO: 우람
    public boolean updateBoard(BoardRequestDTO boardRequestDTO);
    //TODO: 예림

    public boolean deleteBoard(Long boardRequestDTO);

    Page<Board> boardlist(Pageable pageable);
    //TOdO: 영진

}
