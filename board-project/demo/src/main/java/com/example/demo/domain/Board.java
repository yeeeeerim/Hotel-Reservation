package com.example.demo.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //auto_increment

    private String title;

    private String content;

    public void modifyTitleAndContent(String title,String content){
        this.title =title;
        this.content = content;
    }


}
