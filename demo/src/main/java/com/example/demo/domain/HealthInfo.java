package com.example.demo.domain;


import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Entity
@Table(name = "health_info")
public class HealthInfo {

    @Id //기본키 지정. @colomn을 지정하지않으면 열이름을 기본키 속성 또는 필드의 이름으로 가정.
    /*@GenerationValue는 PK 값에 대한 생성 전략을 제공한다.@id와 함께 엔티티 또는 매핑된
    슈퍼클래스의 기본키 속성또는 필드에 적용가능하다.
    generationType은 table, sequesce, identity, auto등이 있는데 여기선 identity로 지정되었다.
    즉, 기본키 생성을 데이터베이스에 위임하는 것. mysql을 사용하기 때문에 auto_increment가 사용되어
    기본키를 생성.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand_name;

    private String land_number;

    private String road_number;

    private String category;


    public void modifyContent(String brandName, String landNumber, String roadNumber, String category) {
        this.brand_name = brandName;
        this.land_number = landNumber;
        this.road_number = roadNumber;
        this.category = category;
    }
}

