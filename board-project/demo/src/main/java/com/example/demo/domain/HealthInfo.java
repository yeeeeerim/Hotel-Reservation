package com.example.demo.domain;


import lombok.*;

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

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String brand_name;

        private String land_number;

        private String road_number;


        private String category;

        public void modifyHealthinfo(String brand_name,String land_number,String road_number, String category){
                this.brand_name=brand_name;
                this.land_number=land_number;
                this.road_number=road_number;
                this.category=category;
        }

        @OneToMany(mappedBy = "healthInfo")
        private List<Reply> replyList;
}
