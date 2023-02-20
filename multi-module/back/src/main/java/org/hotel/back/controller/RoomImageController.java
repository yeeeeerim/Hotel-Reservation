package org.hotel.back.controller;


import lombok.RequiredArgsConstructor;
import org.hotel.back.data.dto.FileDTO;
import org.hotel.back.data.dto.UploadDTO;
import org.hotel.back.domain.RoomImage;
import org.hotel.back.service.RoomService;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RoomImageController {

        private final RoomService roomService;

        //
        //저장한 걸 바로 화면에 뿌리려고 리턴 list
        @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<List<FileDTO>> saveFile(UploadDTO dto){
                var response = roomService.upload(dto);
                if(ObjectUtils.isEmpty(response)){
                        return ResponseEntity.ok(new ArrayList<FileDTO>());
                }
                return ResponseEntity.ok(response);
        }

        @GetMapping("/view")    //여기에 요청 넣는 거에요
        public ResponseEntity<Resource> viewFile(@RequestParam String fileName){
                var response = roomService.viewFile(fileName);
                if(ObjectUtils.isEmpty(response)){
                        return ResponseEntity.internalServerError().build();
                }
                System.out.println(fileName);
                return ResponseEntity.ok().headers(response.getHeaders()).body(response.getResource());

        }
        @DeleteMapping("/delete")
        public Map<String,Boolean> deleteFile(@RequestParam String fileName){

                return roomService.removeFile(fileName);
        }

        @ExceptionHandler(FileNotFoundException.class)
        public ResponseEntity<Resource> errorImage(){
                return ResponseEntity.ok(roomService.viewFile("404.jpg").getResource());
        }



}
