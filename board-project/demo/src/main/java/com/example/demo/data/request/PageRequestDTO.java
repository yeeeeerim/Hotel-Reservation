package com.example.demo.data.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.awt.print.Pageable;
//우람님 dm에 있던 코드
@Data
public class PageRequestDTO {
    private int page; //첫번째 페이지
    private int recordSize; //페이지당 게시글 수
    private int pageSize; // 화면 하단에 출력할 페이지 수

    public PageRequestDTO(){
        this.page = 1;
        this.recordSize = 10;
        this.pageSize = 10;
    }
    public int getRecordSize(){
        return (page - 1) * recordSize;
    }

}
