package com.example.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

        @GetMapping("/data")
        public ResponseEntity<String> date(){
                return ResponseEntity.ok("data");
        }
}
