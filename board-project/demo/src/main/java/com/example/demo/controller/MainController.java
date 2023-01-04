package com.example.demo.controller;


import com.example.demo.data.request.BoardRequestDTO;
import com.example.demo.data.request.PageRequestDTO;
import com.example.demo.data.response.BoardResponseDTO;

import com.example.demo.domain.Board;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;


import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.rmi.server.ExportException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

@Slf4j
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
public ResponseEntity<List<Board>> getPaging(@PageableDefault(
        size = 5, sort = "id", direction = Sort.Direction.DESC)
                                                 Pageable pageable){
  //page=0&sort=id,ASC

    log.info("pageable.getPageSize() ==> {}",pageable.getPageSize());   // 지정한 size
    log.info("pageable.getPageNumber() ==> {}",pageable.getPageNumber());   //요청들어온 page=2
    log.info("pageable.getOffset() ==> {}",pageable.getOffset());   //어디서부터 가져올지

    var list = boardService.boardlist(pageable)
            .stream().collect(Collectors.toList());

    return ResponseEntity.ok(list);
}

  // 예림 - Controller 요청받아 Service에 데이터요청
    //우람 - PageDTO
}
