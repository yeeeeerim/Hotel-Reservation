package com.example.demo.controller;



import com.example.demo.data.request.HealthRequestDTO;
import com.example.demo.data.response.HealthResponseDTO;
import com.example.demo.service.HealthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    private final HealthService healthService;

  @GetMapping("/read")
  public ResponseEntity<HealthResponseDTO> getRead(@RequestParam Long id){
            return ResponseEntity.ok(healthService.read(id));
  } // naver.com/query=검색어
    //localhost:8080/read?id=3 query String

    @PostMapping("/save")
    public ResponseEntity<Boolean> postSave(@RequestBody HealthRequestDTO healthRequestDTO){
            try{
                healthService.saveHealth(healthRequestDTO);
            }catch (Exception e){
                return ResponseEntity.ok(false);
            }

        return ResponseEntity.ok(true);
    }

    @PutMapping("/modify")
    public ResponseEntity<Boolean> updateSave(@RequestBody HealthRequestDTO healthRequestDTO){
        try{
            healthService.updateHealth(healthRequestDTO);
        }catch (Exception e){
            return ResponseEntity.ok(false);
        }

        return ResponseEntity.ok(true);
    }

  @DeleteMapping("/delete")
  public ResponseEntity<Boolean> getDelete(@RequestParam Long id){
            return ResponseEntity.ok(healthService.deleteHealth(id));
  }

//@GetMapping("/list")
//public ResponseEntity<List<Board>> getPaging(@PageableDefault(
//        size = 5, sort = "id", direction = Sort.Direction.DESC)
//                                                 Pageable pageable){
//  //page=0&sort=id,ASC
//
//    log.info("pageable.getPageSize() ==> {}",pageable.getPageSize());   // 지정한 size
//    log.info("pageable.getPageNumber() ==> {}",pageable.getPageNumber());   //요청들어온 page=2
//    log.info("pageable.getOffset() ==> {}",pageable.getOffset());   //어디서부터 가져올지
//
//    var list = boardService.boardlist(pageable)
//            .stream().collect(Collectors.toList());
//
//    return ResponseEntity.ok(list);
//}

  // 예림 - Controller 요청받아 Service에 데이터요청
    //우람 - PageDTO
}
