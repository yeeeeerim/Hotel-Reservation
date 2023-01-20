package com.example.demo.service.impl;

import com.example.demo.data.request.BoardRequestDTO;
import com.example.demo.data.response.BoardResponseDTO;
import com.example.demo.domain.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
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
        boardRepository.save(BoardRequestDTO.toEntity(boardRequestDTO));
        return false;
    }

    @Override
    public boolean updateBoard(BoardRequestDTO boardRequestDTO)
    {
        Board board = boardRepository.findById(boardRequestDTO.getId())
                .orElseThrow(RuntimeException::new);

        board.modifyTitleAndContent(boardRequestDTO.getTitle(), boardRequestDTO.getContent());

        try{
            log.info("updated Data : {}",board);
            boardRepository.save(board);

        }catch (Exception e){
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteBoard(Long id) {
        boardRepository.deleteById(id);
        return false;
    }

    //영진님 슬랙dm에 있던 코드, 영진 - 메소드를 통해 데이터를 가져옴


    @Override
    public Page<Board> boardlist(Pageable pageable){
        return boardRepository.findAll(pageable);
    }


}
