package com.example.demo.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "board")
    private List<Reply> reply;


}
