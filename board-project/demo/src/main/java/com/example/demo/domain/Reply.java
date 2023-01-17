package com.example.demo.domain;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;



@Data
@Entity(name = "reply")
@Builder
@Getter
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long replyId;
    @NotNull
    @Column(nullable = false,length = 255)
    private String content;
    @ManyToOne
    @JoinColumn(name = "id")
    private Board board;
    @ManyToOne
    @JoinColumn(name = "id")
    private HealthInfo healthInfo;

    @CreationTimestamp
    private Timestamp createDate;
}
