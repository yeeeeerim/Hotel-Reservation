package com.example.demo.controller;


import com.example.demo.data.request.BoardRequestDTO;
import com.example.demo.data.request.PageRequestDTO;
import com.example.demo.data.response.BoardResponseDTO;

import com.example.demo.domain.Board;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;


import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.rmi.server.ExportException;
import org.springframework.data.domain.Page;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final BoardService boardService;

    /*
    *  @PageableDefault
    *  Pageable,PageRequest - jpa
    *   mysql limit 10 10
    *  Page<Board> boardList = boardRepository.findAll(Pageable pageable);
    *  Page --> List 변환가능
    *
    * */
  @GetMapping("/read")  //김재우
  public ResponseEntity<BoardResponseDTO> getRead(@RequestParam Long id){
            return ResponseEntity.ok(boardService.read(id));
  } // naver.com/query=검색어
    //localhost:8080/read?id=3 query String

    @PostMapping("/save")
    public ResponseEntity<Boolean> postSave(@RequestBody BoardRequestDTO requestDTO){
            try{
                boardService.saveBoard(requestDTO);
            }catch (Exception e){
                return ResponseEntity.ok(false);
            }

        return ResponseEntity.ok(true);
    }
    // POST(우람), PUT(예림), DELETE(영진)
    @PutMapping("/modify")
    public ResponseEntity<Boolean> updateSave(@RequestBody BoardRequestDTO requestDTO){
        try{
            boardService.updateBoard(requestDTO);
        }catch (Exception e){
            return ResponseEntity.ok(false);
        }

        return ResponseEntity.ok(true);
    }

  @DeleteMapping("/delete") //송영진
  public ResponseEntity<Boolean> getDelete(@RequestParam Long id){
            return ResponseEntity.ok(boardService.deleteBoard(id));
  }

    @GetMapping("/list")
    public Page<Board> getPaging(@RequestParam PageRequestDTO pagedto){

        int page=pagedto.getPage();
        return boardService.boardlist(page);

    }

  // 예림 - Controller 요청받아 Service에 데이터요청
    //우람 - PageDTO
}
