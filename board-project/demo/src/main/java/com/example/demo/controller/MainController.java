package com.example.demo.controller;


import com.example.demo.data.request.BoardRequestDTO;
import com.example.demo.data.response.BoardResponseDTO;

import com.example.demo.domain.Board;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;


import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final BoardService boardService;


  @GetMapping("/read")  //김재우
  public ResponseEntity<BoardResponseDTO> getRead(@RequestParam Long id){
            return ResponseEntity.ok(boardService.read(id));
  } // naver.com/query=검색어

    @PostMapping("/save/{number}")
    public ResponseEntity<Boolean> postSave(@RequestBody BoardRequestDTO requestDTO,
                                            @PathVariable String number){

        boardService.saveBoard(requestDTO);
        return ResponseEntity.ok(false);
    }
  // POST(우람), PUT(예림), DELETE(영진)

      @DeleteMapping("/delete") //송영진
      public ResponseEntity<Boolean> getDelete(@RequestParam Long id){
                return ResponseEntity.ok(boardService.deleteBoard(id));
      }

}
