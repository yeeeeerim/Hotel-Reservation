package com.example.demo.domain;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Reply {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long replyId;
    private String writer;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_id") // healthinfo entity의 테이블 중 어떤 컬럼과 매핑할지 지정하기 위함
    @ToString.Exclude // 무한루프 방지
    private HealthInfo healthInfo;
}
