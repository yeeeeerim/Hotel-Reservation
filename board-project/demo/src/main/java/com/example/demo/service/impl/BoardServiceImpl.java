package com.example.demo.service.impl;

import com.example.demo.data.request.BoardRequestDTO;
import com.example.demo.data.response.BoardResponseDTO;
import com.example.demo.domain.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    @Override
    public BoardResponseDTO read(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return BoardResponseDTO.builder()
                .id(board.getId())
                .content(board.getContent())
                .title(board.getTitle())
                .build();
    }

    @Override
    public boolean saveBoard(BoardRequestDTO boardRequestDTO) {
       // boardRepository.save();
        return false;
    }

    @Override
    public BoardResponseDTO updateBoard(BoardRequestDTO boardRequestDTO) {
        return null;
    }

    @Override
    public boolean deleteBoard(BoardRequestDTO boardRequestDTO) {

        boardRepository.deleteById(boardRequestDTO.getId());

        return false;
    }
}
