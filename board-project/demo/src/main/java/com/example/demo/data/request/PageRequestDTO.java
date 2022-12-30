package com.example.demo.data.request;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.awt.print.Pageable;
//우람님 dm에 있던 코드
@Data

public class PageRequestDTO {
    private int page; //첫번째 페이지
    private int size; //페이지당 데이터수

    public PageRequestDTO(){
        this.page = 1;
        this.size = 10;
    }
//    public Pageable getPageable(Sort sort){
//        return PageRequest.of(page -1, size,sort);
//
//    }
}
