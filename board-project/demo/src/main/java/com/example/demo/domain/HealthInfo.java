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


}
