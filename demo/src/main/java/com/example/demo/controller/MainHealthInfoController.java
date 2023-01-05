package com.example.demo.controller;

import com.example.demo.data.request.HealthInfoRequestDTO;
import com.example.demo.data.response.HealthInfoResponseDTO;
import com.example.demo.domain.HealthInfo;
import com.example.demo.service.HealthInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainHealthInfoController {
    //브라우저에서 요청받아서 서비스에 구현된 기능들에 반환
    private final HealthInfoService healthInfoService;
    /*
    ResponseEntity 다시공부.ok는 뭐임
    왜 포스트랑 풋은 파라미터가아니라 바디로 받아야하는가? id를 조회해서 찾는게 아니라 저장할거라
    Exception e는 무엇? false로 반환하면 어떻게됨?


     */
    @GetMapping("/read") // url에 /read라고 띄워줌. 파라미터값으로 id를 받아서 반환함.
    public ResponseEntity<HealthInfoResponseDTO> getRead(@RequestParam Long id){
        return ResponseEntity.ok(healthInfoService.read(id));
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> postSave(@RequestBody HealthInfoRequestDTO healthInfoRequestDTO){
        try{
            healthInfoService.saveHealth(healthInfoRequestDTO); //네
        }catch (Exception e){ //e는 무엇?
            return ResponseEntity.ok(false); //ok로 반환하면어떻게 됨?
        }
        return ResponseEntity.ok(true);
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> updateSave(@RequestBody HealthInfoRequestDTO healthInfoRequestDTO){
        try{
            healthInfoService.updateHealth(healthInfoRequestDTO);
        }catch(Exception e){
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> getDelete(@RequestParam Long id){
        return ResponseEntity.ok(healthInfoService.deleteHealth(id));
    }

    //페이징 처리
//    @GetMapping("/list")
//    public ResponseEntity<List<HealthInfo>> getPaging(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction
//            .DESC)Pageable pageable){
//        log.info("pageable.getPageSize() ==> {}",pageable.getPageSize());   // 지정한 size
//        log.info("pageable.getPageNumber() ==> {}",pageable.getPageNumber());   //요청들어온 page=2
//        log.info("pageable.getOffset() ==> {}",pageable.getOffset());   //어디서부터 가져올지
//
//        var list = healthInfoService.healthInfoList(pageable)
//                .stream().collect(Collectors.toList());
//
//        return ResponseEntity.ok(list);
//    }
}

