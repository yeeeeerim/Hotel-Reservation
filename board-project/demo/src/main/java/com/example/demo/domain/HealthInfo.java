package com.example.demo.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
@Entity
@Table(name = "health_info")
public class HealthInfo {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String brand_name;
        private String land_number;
        private String road_number;
        private String category;


        @OneToMany(mappedBy = "healthInfo")
        @ToString.Exclude //무한루프 방지
        private List<Reply> replies = new ArrayList<>();

        public void modifyHealthinfo(String brand_name,String land_number,String road_number, String category){
                this.brand_name=brand_name;
                this.land_number=land_number;
                this.road_number=road_number;
                this.category=category;
        }
}
