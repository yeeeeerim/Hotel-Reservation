package com.example.demo.data.request;

import com.example.demo.domain.HealthInfo;
import lombok.Getter;

@Getter
public class HealthRequestDTO {
    private Long id;
    private String brand_name;
    private String land_number;
    private String road_number;
    private String category;


    public static HealthInfo toEntity(HealthRequestDTO dto){
        return HealthInfo.builder()
                .id(dto.getId())
                .brand_name(dto.getBrand_name())
                .category(dto.getCategory())
                .land_number(dto.getLand_number())
                .road_number(dto.getRoad_number())
                .build();
    }


}
